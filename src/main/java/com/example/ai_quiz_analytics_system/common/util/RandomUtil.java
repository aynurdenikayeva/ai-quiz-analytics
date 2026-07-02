package com.example.ai_quiz_analytics_system.common.util;

import java.util.UUID;

public class RandomUtil {

    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    public static int randomInt(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
