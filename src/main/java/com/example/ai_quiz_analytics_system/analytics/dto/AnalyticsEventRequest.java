package com.example.ai_quiz_analytics_system.analytics.dto;


public class AnalyticsEventRequest {

    private String userId;
    private String eventType;

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getEventType() { return eventType; }
    public void setEventType(String eventType) { this.eventType = eventType; }
}
