package com.example.ai_quiz_analytics_system.auth.dto;

public class AuthResponse {
    public String token;
    public String refreshToken;
    public String role;

    public AuthResponse(String token, String refreshToken, String role) {
        this.token = token;
        this.refreshToken = refreshToken;
        this.role = role;
    }
}