package com.example.ai_quiz_analytics_system.redis.service;

import com.example.ai_quiz_analytics_system.redis.util.RedisKeyBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RedisAnalyticsCacheService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisAnalyticsCacheService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void incrementMetric(String metric) {

        String key = RedisKeyBuilder.analyticsKey(metric);

        redisTemplate.opsForValue().increment(key);
    }

    public Object getMetric(String metric) {

        String key = RedisKeyBuilder.analyticsKey(metric);

        return redisTemplate.opsForValue().get(key);
    }
}
