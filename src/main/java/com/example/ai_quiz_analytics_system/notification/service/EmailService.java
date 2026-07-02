package com.example.ai_quiz_analytics_system.notification.service;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendEmail(String to, String subject, String message) {

        // Real projectdə burada SMTP / SendGrid / AWS SES olacaq
        System.out.println("Sending EMAIL:");
        System.out.println("TO: " + to);
        System.out.println("SUBJECT: " + subject);
        System.out.println("MESSAGE: " + message);
    }
}
