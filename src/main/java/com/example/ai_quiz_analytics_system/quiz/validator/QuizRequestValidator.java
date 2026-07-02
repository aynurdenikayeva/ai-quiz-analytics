package com.example.ai_quiz_analytics_system.quiz.validator;

import com.example.ai_quiz_analytics_system.common.exception.ValidationException;
import com.example.ai_quiz_analytics_system.quiz.dto.GenerateQuizRequest;
import org.springframework.stereotype.Component;

@Component
public class QuizRequestValidator {

    public void validate(GenerateQuizRequest request) {

        if (request == null) {
            throw new ValidationException("Request cannot be null.");
        }

        if (request.getTopic() == null ||
                request.getTopic().isBlank()) {

            throw new ValidationException("Topic is required.");
        }

        if (request.getQuestionCount() < 1 ||
                request.getQuestionCount() > 20) {

            throw new ValidationException(
                    "Question count must be between 1 and 20."
            );
        }

        if (request.getDifficulty() == null) {
            throw new ValidationException(
                    "Difficulty is required."
            );
        }
    }
}