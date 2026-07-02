package com.example.ai_quiz_analytics_system.ai.fallback;
import com.example.ai_quiz_analytics_system.ai.dto.GeneratedQuestionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FallbackQuestionService {

    public List<GeneratedQuestionDto> getFallback(String topic) {

        GeneratedQuestionDto q = new GeneratedQuestionDto();
        q.setQuestion("What is a basic concept of " + topic + "?");
        q.setOptions(List.of("A", "B", "C", "D"));
        q.setCorrectAnswer("A");

        return List.of(q);
    }
}