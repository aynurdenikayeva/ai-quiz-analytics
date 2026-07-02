package com.example.ai_quiz_analytics_system.ai.util;


import org.springframework.stereotype.Component;

@Component
public class JsonCleaner {

    public String clean(String text) {

        if (text == null) return "{}";

        // remove markdown code blocks
        text = text.replace("```json", "")
                .replace("```", "")
                .trim();

        // extract JSON part only
        int start = text.indexOf("{");
        int end = text.lastIndexOf("}");

        if (start != -1 && end != -1 && end > start) {
            return text.substring(start, end + 1);
        }

        return "{}";
    }
}
