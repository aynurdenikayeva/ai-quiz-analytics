package com.example.ai_quiz_analytics_system.redis.util;

public class RedisKeyBuilder {

    public static String rateLimitKey(String userId) {
        return "rate_limit:" + userId;
    }

    public static String blacklistKey(String token) {
        return "blacklist:" + token;
    }

    public static String analyticsKey(String metric) {
        return "analytics:" + metric;
    }

    public static String cacheKey(String prefix, String value) {
        return prefix + ":" + value;
    }
}
