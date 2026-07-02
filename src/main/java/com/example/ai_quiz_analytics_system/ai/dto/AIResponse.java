package com.example.ai_quiz_analytics_system.ai.dto;


public class AIResponse {

    private String generatedText;

    public AIResponse() {}

    public AIResponse(String generatedText) {
        this.generatedText = generatedText;
    }

    public String getGeneratedText() { return generatedText; }
    public void setGeneratedText(String generatedText) { this.generatedText = generatedText; }
}