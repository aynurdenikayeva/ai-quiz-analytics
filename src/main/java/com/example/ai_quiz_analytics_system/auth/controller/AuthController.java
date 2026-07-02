package com.example.ai_quiz_analytics_system.auth.controller;

import com.example.ai_quiz_analytics_system.auth.dto.AuthResponse;
import com.example.ai_quiz_analytics_system.auth.dto.LoginRequest;
import com.example.ai_quiz_analytics_system.auth.dto.RefreshTokenRequest;
import com.example.ai_quiz_analytics_system.auth.dto.RegisterRequest;
import com.example.ai_quiz_analytics_system.auth.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody RefreshTokenRequest request) {
        return authService.refreshToken(request);
    }
}
