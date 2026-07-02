package com.example.ai_quiz_analytics_system.ai.service;

import com.example.ai_quiz_analytics_system.ai.dto.GeneratedQuestionDto;
import org.springframework.stereotype.Service;


@Service
public class ExplanationGeneratorService {

    public String generateExplanation(GeneratedQuestionDto question) {

        return "The correct answer is '" + question.getCorrectAnswer()
                + "' because it is the most accurate and relevant option based on the topic: "
                + question.getQuestion();
    }
}