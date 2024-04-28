package com.example.SpringBootTMS.work52.service;

import com.example.SpringBootTMS.work52.repository.UserRepository;
import com.example.SpringBootTMS.work52.dto.JwtTokenResponse;
import com.example.SpringBootTMS.work52.dto.LogInRequest;
import com.example.SpringBootTMS.work52.dto.RegisterRequest;
import com.example.SpringBootTMS.work52.model.Role;
import com.example.SpringBootTMS.work52.model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtTokenResponse register(RegisterRequest request) {
        var user = User.builder().username(request.getUsername())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtTokenResponse.builder().jwtToken(jwt).build();
    }

    public JwtTokenResponse logIn(LogInRequest request) throws IllegalArgumentException {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            var jwt = jwtService.generateToken(userRepository.findByUsername(request.getUsername()).get());
            return JwtTokenResponse.builder().jwtToken(jwt).build();

        } catch (AuthenticationException e) {
            logger.info(e.getMessage());
            throw new IllegalArgumentException("Invalid username or password");
        }
    }
}
