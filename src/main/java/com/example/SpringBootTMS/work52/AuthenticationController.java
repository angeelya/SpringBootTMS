package com.example.SpringBootTMS.work52;

import com.example.SpringBootTMS.work52.dto.JwtTokenResponse;
import com.example.SpringBootTMS.work52.dto.LogInRequest;
import com.example.SpringBootTMS.work52.dto.RegisterRequest;
import com.example.SpringBootTMS.work52.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/register")
    public ResponseEntity<JwtTokenResponse> register(@RequestBody RegisterRequest registerRequest)
    {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }
    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponse> login(@RequestBody LogInRequest logInRequest)
    {
        return ResponseEntity.ok(authenticationService.logIn(logInRequest));
    }
    @GetMapping("/hello")
    public ResponseEntity<String> hello()
    {
        return ResponseEntity.ok("Hello");
    }
}
