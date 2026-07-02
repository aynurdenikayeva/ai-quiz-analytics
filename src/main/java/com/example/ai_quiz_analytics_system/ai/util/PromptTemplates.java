package com.example.ai_quiz_analytics_system.ai.util;

public class PromptTemplates {

    public static String quizPrompt(String topic, int count, String difficulty) {

        return """
        Generate %d multiple choice questions about %s.
        Difficulty level: %s.

        Return ONLY valid JSON in this format:
        {
          "questions": [
            {
              "question": "",
              "options": ["A", "B", "C", "D"],
              "answer": ""
            }
          ]
        }
        """.formatted(count, topic, difficulty);
    }
}