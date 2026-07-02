package com.example.ai_quiz_analytics_system.ai.service;

import com.example.ai_quiz_analytics_system.ai.util.PromptTemplates;
import org.springframework.stereotype.Service;

@Service
public class PromptBuilder {

    public String build(String topic, int count, String difficulty) {
        return PromptTemplates.quizPrompt(topic, count, difficulty);
    }
}