package com.example.ai_quiz_analytics_system.websocket;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketSessionRegistry {

    private final Map<String, String> userSessions = new ConcurrentHashMap<>();

    public void register(String userId, String sessionId) {
        userSessions.put(userId, sessionId);
    }

    public void remove(String userId) {
        userSessions.remove(userId);
    }

    public String getSession(String userId) {
        return userSessions.get(userId);
    }

    public Map<String, String> getAll() {
        return userSessions;
    }
}
