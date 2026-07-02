package com.example.ai_quiz_analytics_system.analytics.service;

import com.example.ai_quiz_analytics_system.analytics.entity.MetricSnapshot;
import com.example.ai_quiz_analytics_system.analytics.repository.MetricsRepository;
import org.springframework.stereotype.Service;

import org.springframework.scheduling.annotation.Scheduled;

import java.util.Map;

@Service
public class AnalyticsPersistenceService {

    private final MetricsAggregator aggregator;
    private final MetricsRepository repository;

    public AnalyticsPersistenceService(MetricsAggregator aggregator,
                                       MetricsRepository repository) {
        this.aggregator = aggregator;
        this.repository = repository;
    }

    @Scheduled(fixedRate = 60000)
    public void persistMetrics() {

        for (Map.Entry<String, Long> entry : aggregator.getMetrics().entrySet()) {

            MetricSnapshot snapshot = new MetricSnapshot();
            snapshot.setMetricName(entry.getKey());
            snapshot.setValue(entry.getValue());

            repository.save(snapshot);
        }
    }
}
