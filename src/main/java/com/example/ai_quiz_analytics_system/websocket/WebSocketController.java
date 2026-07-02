package com.example.ai_quiz_analytics_system.websocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/events")
    @SendTo("/topic/analytics")
    public String handleEvent(String message) {

        // incoming event (click/view etc.)
        return "Event received: " + message;
    }
}
