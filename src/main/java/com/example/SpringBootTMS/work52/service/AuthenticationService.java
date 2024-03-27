package com.example.SpringBootTMS.work52.service;

import com.example.SpringBootTMS.work52.repository.UserRepository;
import com.example.SpringBootTMS.work52.dto.JwtTokenResponse;
import com.example.SpringBootTMS.work52.dto.LogInRequest;
import com.example.SpringBootTMS.work52.dto.RegisterRequest;
import com.example.SpringBootTMS.work52.model.Role;
import com.example.SpringBootTMS.work52.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
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
        return JwtTokenResponse .builder().jwtToken(jwt).build();
    }

    public JwtTokenResponse  logIn(LogInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));
        var jwt = jwtService.generateToken(user);
        return JwtTokenResponse.builder().jwtToken(jwt).build();
    }
}
