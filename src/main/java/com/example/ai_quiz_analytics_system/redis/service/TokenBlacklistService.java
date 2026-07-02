package com.example.ai_quiz_analytics_system.redis.service;

import com.example.ai_quiz_analytics_system.redis.util.RedisKeyBuilder;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class TokenBlacklistService {

    private final RedisTemplate<String, Object> redisTemplate;

    public TokenBlacklistService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void blacklist(String token) {

        String key = RedisKeyBuilder.blacklistKey(token);

        redisTemplate.opsForValue().set(key, "blacklisted", Duration.ofHours(24));
    }

    public boolean isBlacklisted(String token) {

        String key = RedisKeyBuilder.blacklistKey(token);

        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
}