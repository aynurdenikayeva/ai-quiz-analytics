package com.example.ai_quiz_analytics_system.analytics.dto;


import java.time.LocalDateTime;

public class AnalyticsResponse {

    private String message;
    private LocalDateTime timestamp = LocalDateTime.now();

    public AnalyticsResponse() {}

    public AnalyticsResponse(String message) {
        this.message = message;
    }

    public String getMessage() { return message; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
