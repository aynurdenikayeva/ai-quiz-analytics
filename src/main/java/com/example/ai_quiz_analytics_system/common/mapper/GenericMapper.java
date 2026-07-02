package com.example.ai_quiz_analytics_system.common.mapper;

import org.springframework.stereotype.Component;

@Component
public class GenericMapper {

    public <T> T map(Object source, Class<T> targetClass) {
        try {
            T instance = targetClass.getDeclaredConstructor().newInstance();
            return instance;
        } catch (Exception e) {
            throw new RuntimeException("Mapping failed");
        }
    }
}
