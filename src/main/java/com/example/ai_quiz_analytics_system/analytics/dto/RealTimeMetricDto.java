package com.example.ai_quiz_analytics_system.analytics.dto;
public class RealTimeMetricDto {

    private String metric;
    private long value;

    public RealTimeMetricDto() {}

    public RealTimeMetricDto(String metric, long value) {
        this.metric = metric;
        this.value = value;
    }

    public String getMetric() { return metric; }
    public void setMetric(String metric) { this.metric = metric; }

    public long getValue() { return value; }
    public void setValue(long value) { this.value = value; }
}
