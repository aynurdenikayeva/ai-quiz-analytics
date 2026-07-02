package com.example.ai_quiz_analytics_system.notification.service;
import com.example.ai_quiz_analytics_system.notification.dto.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final EmailService emailService;

    public NotificationService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void send(NotificationRequest request) {

        switch (request.getType()) {

            case "EMAIL" -> emailService.sendEmail(
                    request.getTo(),
                    request.getSubject(),
                    request.getMessage()
            );

            case "SYSTEM" -> System.out.println(
                    "SYSTEM NOTIFICATION: " + request.getMessage()
            );

            default -> throw new RuntimeException("Unsupported notification type");
        }
    }
}
