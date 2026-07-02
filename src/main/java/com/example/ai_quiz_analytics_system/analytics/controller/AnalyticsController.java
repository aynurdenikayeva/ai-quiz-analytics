package com.example.ai_quiz_analytics_system.analytics.controller;


import com.example.ai_quiz_analytics_system.analytics.dto.AnalyticsEventRequest;
import com.example.ai_quiz_analytics_system.analytics.dto.MetricsSnapshot;
import com.example.ai_quiz_analytics_system.analytics.service.AnalyticsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @PostMapping("/event")
    public void collectEvent(@RequestBody AnalyticsEventRequest request) {
        analyticsService.collectEvent(request);
    }

    @GetMapping("/metrics")
    public MetricsSnapshot getMetrics() {
        return analyticsService.getMetrics();
    }
}