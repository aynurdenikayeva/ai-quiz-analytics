package com.example.ai_quiz_analytics_system.analytics.websocket.WebSocketPublisher;

import com.example.ai_quiz_analytics_system.analytics.dto.RealTimeMetricDto;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketPublisher {

    private final SimpMessagingTemplate template;

    public WebSocketPublisher(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void publish(String metric, long value) {

        template.convertAndSend(
                "/topic/analytics",
                new RealTimeMetricDto(metric, value)
        );
    }
}