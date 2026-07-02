package com.example.ai_quiz_analytics_system.analytics.service;

import com.example.ai_quiz_analytics_system.analytics.dto.MetricsSnapshot;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
public class MetricsAggregator {

    private final Map<String, Long> metrics = new ConcurrentHashMap<>();

    public void increment(String key) {
        metrics.merge(key, 1L, Long::sum);
    }

    public Map<String, Long> getMetrics() {
        return metrics;
    }
}
