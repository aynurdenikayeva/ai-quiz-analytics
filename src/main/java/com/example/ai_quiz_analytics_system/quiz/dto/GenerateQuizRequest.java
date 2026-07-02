package com.example.ai_quiz_analytics_system.quiz.dto;

import com.example.ai_quiz_analytics_system.quiz.entity.Difficulty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GenerateQuizRequest {

    @NotBlank(message = "Topic is required")
    private String topic;

    @Min(value = 1, message = "Minimum question count is 1")
    @Max(value = 20, message = "Maximum question count is 20")
    private int questionCount;

    @NotNull(message = "Difficulty is required")
    private Difficulty difficulty;

    public GenerateQuizRequest() {
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }
}