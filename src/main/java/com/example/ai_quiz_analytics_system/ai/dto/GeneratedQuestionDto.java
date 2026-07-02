package com.example.ai_quiz_analytics_system.ai.dto;

import java.util.List;

public class GeneratedQuestionDto {

    private String question;
    private List<String> options;
    private String correctAnswer;

    public GeneratedQuestionDto() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}