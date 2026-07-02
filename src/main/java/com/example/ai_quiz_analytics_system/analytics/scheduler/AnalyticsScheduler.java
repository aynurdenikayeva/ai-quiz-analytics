package com.example.ai_quiz_analytics_system.analytics.scheduler.AnalyticsScheduler;

import com.example.ai_quiz_analytics_system.analytics.service.MetricsAggregator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AnalyticsScheduler {

    private final MetricsAggregator aggregator;
    private final com.example.ai_quiz_analytics_system.analytics.websocket.WebSocketPublisher.WebSocketPublisher publisher;

    public AnalyticsScheduler(MetricsAggregator aggregator,
                              com.example.ai_quiz_analytics_system.analytics.websocket.WebSocketPublisher.WebSocketPublisher publisher) {
        this.aggregator = aggregator;
        this.publisher = publisher;
    }

    @Scheduled(fixedRate = 5000)
    public void pushRealtimeMetrics() {

        aggregator.getMetrics()
                .forEach(publisher::publish);
    }
}
