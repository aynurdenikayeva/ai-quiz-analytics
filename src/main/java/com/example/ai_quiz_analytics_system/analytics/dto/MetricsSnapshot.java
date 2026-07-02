package com.example.ai_quiz_analytics_system.analytics.dto;

import java.util.Map;

public class MetricsSnapshot {

    private Map<String, Long> metrics;

    public MetricsSnapshot() {}

    public MetricsSnapshot(Map<String, Long> metrics) {
        this.metrics = metrics;
    }

    public Map<String, Long> getMetrics() { return metrics; }
    public void setMetrics(Map<String, Long> metrics) { this.metrics = metrics; }
}