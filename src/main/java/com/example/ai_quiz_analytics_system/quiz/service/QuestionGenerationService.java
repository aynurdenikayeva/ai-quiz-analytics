package com.example.ai_quiz_analytics_system.quiz.service;

import com.example.ai_quiz_analytics_system.ai.dto.GeneratedQuestionDto;
import com.example.ai_quiz_analytics_system.ai.service.AIQuestionService;
import com.example.ai_quiz_analytics_system.quiz.dto.GenerateQuizRequest;
import com.example.ai_quiz_analytics_system.quiz.entity.ModerationStatus;
import com.example.ai_quiz_analytics_system.quiz.entity.Question;
import com.example.ai_quiz_analytics_system.quiz.entity.QuestionOption;
import com.example.ai_quiz_analytics_system.quiz.entity.QuestionType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionGenerationService {

    private final AIQuestionService aiQuestionService;

    public QuestionGenerationService(AIQuestionService aiQuestionService) {
        this.aiQuestionService = aiQuestionService;
    }

    /**
     * Generate questions using HuggingFace AI.
     */
    public List<Question> generateQuestions(GenerateQuizRequest request) {

        List<GeneratedQuestionDto> generatedQuestions =
                aiQuestionService.generateQuestions(
                        request.getTopic(),
                        request.getQuestionCount(),
                        request.getDifficulty()
                );

        List<Question> questions = new ArrayList<>();

        for (GeneratedQuestionDto dto : generatedQuestions) {

            Question question = new Question();

            question.setQuestionText(dto.getQuestion());

            question.setDifficulty(request.getDifficulty());

            question.setQuestionType(QuestionType.MULTIPLE_CHOICE);

            question.setModerationStatus(ModerationStatus.PENDING);

            for (String option : dto.getOptions()) {

                QuestionOption questionOption =
                        new QuestionOption();

                questionOption.setOptionText(option);

                questionOption.setCorrect(
                        option.equals(dto.getCorrectAnswer())
                );

                question.addOption(questionOption);
            }

            questions.add(question);
        }

        return questions;
    }

}