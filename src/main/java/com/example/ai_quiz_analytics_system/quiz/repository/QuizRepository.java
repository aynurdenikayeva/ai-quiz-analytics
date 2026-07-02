package com.example.ai_quiz_analytics_system.quiz.repository;

import com.example.ai_quiz_analytics_system.quiz.entity.Difficulty;
import com.example.ai_quiz_analytics_system.quiz.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

    List<Quiz> findByTopicContainingIgnoreCase(String topic);

    List<Quiz> findByDifficulty(Difficulty difficulty);

}