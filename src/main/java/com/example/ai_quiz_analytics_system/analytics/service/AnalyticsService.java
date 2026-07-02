package com.example.ai_quiz_analytics_system.analytics.service;


import com.example.ai_quiz_analytics_system.analytics.dto.AnalyticsEventRequest;
import com.example.ai_quiz_analytics_system.analytics.dto.MetricsSnapshot;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsService {

    private final EventCollector eventCollector;
    private final MetricsAggregator aggregator;

    public AnalyticsService(EventCollector eventCollector,
                            MetricsAggregator aggregator) {
        this.eventCollector = eventCollector;
        this.aggregator = aggregator;
    }

    public void collectEvent(AnalyticsEventRequest request) {

        eventCollector.collect(request);
        aggregator.increment(request.getEventType());
    }

    public MetricsSnapshot getMetrics() {
        return new MetricsSnapshot(aggregator.getMetrics());
    }
}