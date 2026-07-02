package com.example.ai_quiz_analytics_system.quiz.dto;


public class ExplanationResponse {

    private String explanation;

    public ExplanationResponse() {
    }

    public ExplanationResponse(String explanation) {
        this.explanation = explanation;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}