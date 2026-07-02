package com.example.ai_quiz_analytics_system.analytics.repository;

import com.example.ai_quiz_analytics_system.analytics.entity.AnalyticsEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalyticsRepository extends JpaRepository<AnalyticsEvent, Long> {
}
