package com.example.ai_quiz_analytics_system.ai.parser;
import com.example.ai_quiz_analytics_system.ai.dto.GeneratedQuestionDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AIResponseParser {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<GeneratedQuestionDto> parse(String rawJson) {

        try {
            String cleaned = clean(rawJson);

            var node = objectMapper.readTree(cleaned);
            var questionsNode = node.get("questions");

            GeneratedQuestionDto[] arr =
                    objectMapper.treeToValue(questionsNode, GeneratedQuestionDto[].class);

            return Arrays.asList(arr);

        } catch (Exception e) {
            throw new RuntimeException("AI parsing failed: " + e.getMessage());
        }
    }

    private String clean(String text) {

        if (text == null) return "{}";

        text = text.replace("```json", "")
                .replace("```", "")
                .trim();

        int start = text.indexOf("{");
        int end = text.lastIndexOf("}");

        if (start != -1 && end != -1) {
            return text.substring(start, end + 1);
        }

        return text;
    }
}