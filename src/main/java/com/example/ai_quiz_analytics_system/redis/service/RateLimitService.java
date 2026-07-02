package com.example.ai_quiz_analytics_system.redis.service;

import com.example.ai_quiz_analytics_system.redis.util.RedisKeyBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RateLimitService {

    private final RedisTemplate<String, Object> redisTemplate;

    private static final int LIMIT = 10; // 10 requests per minute

    public RateLimitService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void checkLimit(String userId) {

        String key = RedisKeyBuilder.rateLimitKey(userId);

        Integer count = (Integer) redisTemplate.opsForValue().get(key);

        if (count == null) {
            redisTemplate.opsForValue().set(key, 1, Duration.ofMinutes(1));
            return;
        }

        if (count >= LIMIT) {
            throw new RuntimeException("Rate limit exceeded");
        }

        redisTemplate.opsForValue().increment(key);
    }
}