package com.example.ai_quiz_analytics_system.analytics.repository;

import com.example.ai_quiz_analytics_system.analytics.entity.MetricSnapshot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetricsRepository extends JpaRepository<MetricSnapshot, Long> {
}
