package com.example.ai_quiz_analytics_system.ai.service;

import com.example.ai_quiz_analytics_system.ai.dto.GeneratedQuestionDto;
import com.example.ai_quiz_analytics_system.ai.fallback.FallbackQuestionService;
import com.example.ai_quiz_analytics_system.ai.parser.AIResponseParser;
import com.example.ai_quiz_analytics_system.quiz.entity.Difficulty;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AIQuestionService {

    private final HuggingFaceService hfService;
    private final PromptBuilder promptBuilder;
    private final AIResponseParser parser;
    private final FallbackQuestionService fallbackService;

    public AIQuestionService(HuggingFaceService hfService,
                             PromptBuilder promptBuilder,
                             AIResponseParser parser,
                             FallbackQuestionService fallbackService) {
        this.hfService = hfService;
        this.promptBuilder = promptBuilder;
        this.parser = parser;
        this.fallbackService = fallbackService;
    }

    /**
     * Generates quiz questions using HuggingFace AI.
     */
    public List<GeneratedQuestionDto> generateQuestions(
            String topic,
            int count,
            Difficulty difficulty) {

        try {

            String prompt = promptBuilder.build(
                    topic,
                    count,
                    difficulty.name()
            );

            String response = hfService.generate(prompt);

            return parser.parse(response);

        } catch (Exception e) {

            System.err.println("AI generation failed: " + e.getMessage());

            return fallbackService.getFallback(topic);
        }
    }
}