package com.example.ai_quiz_analytics_system.websocket;


import com.example.ai_quiz_analytics_system.analytics.service.MetricsAggregator;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.CopyOnWriteArraySet;


@Component
public class AnalyticsSocketHandler extends TextWebSocketHandler {

    private final MetricsAggregator metricsAggregator;

    // active connected sessions
    private final CopyOnWriteArraySet<WebSocketSession> sessions = new CopyOnWriteArraySet<>();

    public AnalyticsSocketHandler(MetricsAggregator metricsAggregator) {
        this.metricsAggregator = metricsAggregator;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);

        session.sendMessage(new TextMessage(
                "✅ Connected to Analytics Socket"
        ));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session,
                                     TextMessage message) throws Exception {

        String payload = message.getPayload();

        // expected formats:
        // CLICK
        // VIEW
        // QUIZ_START
        // QUIZ_SUBMIT

        if (payload == null || payload.isBlank()) {
            session.sendMessage(new TextMessage("❌ Empty event"));
            return;
        }

        // update in-memory metrics
        metricsAggregator.increment(payload);

        // acknowledge sender
        session.sendMessage(new TextMessage(
                "📊 Event recorded: " + payload
        ));

        // broadcast to all clients
        broadcast("EVENT: " + payload);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session,
                                      CloseStatus status) {

        sessions.remove(session);

        System.out.println("❌ Client disconnected: " + session.getId());
    }

    private void broadcast(String message) {

        for (WebSocketSession session : sessions) {
            try {
                if (session.isOpen()) {
                    session.sendMessage(new TextMessage(message));
                }
            } catch (Exception e) {
                System.out.println("Broadcast error: " + e.getMessage());
            }
        }
    }
}