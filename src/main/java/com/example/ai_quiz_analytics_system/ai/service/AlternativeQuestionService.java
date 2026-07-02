package com.example.ai_quiz_analytics_system.ai.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlternativeQuestionService {

    public List<String> generateAlternatives(String question) {

        return List.of(
                "Rephrased: " + question,
                "What is the meaning of: " + question,
                "Explain briefly: " + question,
                "Advanced version: " + question
        );
    }
}