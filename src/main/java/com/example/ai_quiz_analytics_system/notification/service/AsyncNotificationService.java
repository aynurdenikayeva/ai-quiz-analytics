package com.example.ai_quiz_analytics_system.notification.service;

import com.example.ai_quiz_analytics_system.notification.dto.NotificationRequest;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncNotificationService {

    private final NotificationService notificationService;

    public AsyncNotificationService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Async
    public void sendAsync(NotificationRequest request) {
        notificationService.send(request);
    }
}