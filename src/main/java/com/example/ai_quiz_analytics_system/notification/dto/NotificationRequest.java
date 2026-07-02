package com.example.ai_quiz_analytics_system.notification.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationRequest {

    private String to;
    private String subject;
    private String message;
    private String type; // EMAIL, SYSTEM, WEBHOOK

    public NotificationRequest() {}

    public NotificationRequest(String to, String subject, String message, String type) {
        this.to = to;
        this.subject = subject;
        this.message = message;
        this.type = type;
    }
}
