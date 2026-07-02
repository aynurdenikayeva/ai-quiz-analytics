package com.example.ai_quiz_analytics_system.quiz.repository;

import com.example.ai_quiz_analytics_system.quiz.entity.ModerationStatus;
import com.example.ai_quiz_analytics_system.quiz.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findByModerationStatus(ModerationStatus status);

}