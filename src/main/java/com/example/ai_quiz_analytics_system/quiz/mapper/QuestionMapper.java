package com.example.ai_quiz_analytics_system.quiz.mapper;

import com.example.ai_quiz_analytics_system.quiz.dto.QuestionResponse;
import com.example.ai_quiz_analytics_system.quiz.entity.Question;
import com.example.ai_quiz_analytics_system.quiz.entity.QuestionOption;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QuestionMapper {

    public QuestionResponse toResponse(Question question) {

        QuestionResponse response = new QuestionResponse();

        response.setId(question.getId());
        response.setQuestion(question.getQuestionText());
        response.setDifficulty(question.getDifficulty().name());
        response.setQuestionType(question.getQuestionType().name());

        List<String> options = question.getOptions()
                .stream()
                .map(QuestionOption::getOptionText)
                .toList();

        response.setOptions(options);

        question.getOptions()
                .stream()
                .filter(QuestionOption::isCorrect)
                .findFirst()
                .ifPresent(option ->
                        response.setCorrectAnswer(option.getOptionText()));

        return response;
    }

}