package com.example.ai_quiz_analytics_system.analytics.service;

import com.example.ai_quiz_analytics_system.analytics.dto.AnalyticsEventRequest;
import com.example.ai_quiz_analytics_system.analytics.entity.AnalyticsEvent;
import com.example.ai_quiz_analytics_system.analytics.repository.AnalyticsRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EventCollector {

    private final AnalyticsRepository repository;

    public EventCollector(AnalyticsRepository repository) {
        this.repository = repository;
    }

    public void collect(AnalyticsEventRequest request) {

        AnalyticsEvent event = new AnalyticsEvent();
        event.setUserId(request.getUserId());
        event.setEventType(request.getEventType());

        repository.save(event);
    }
}