package com.example.ai_quiz_analytics_system.quiz.service;

import com.example.ai_quiz_analytics_system.common.exception.ResourceNotFoundException;
import com.example.ai_quiz_analytics_system.quiz.entity.ModerationStatus;
import com.example.ai_quiz_analytics_system.quiz.entity.Question;
import com.example.ai_quiz_analytics_system.quiz.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class QuizModerationService {

    private final QuestionRepository questionRepository;

    public QuizModerationService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    /**
     * Bütün gözləyən (PENDING) sualları qaytarır.
     */
    @Transactional(readOnly = true)
    public List<Question> getPendingQuestions() {

        return questionRepository.findByModerationStatus(
                ModerationStatus.PENDING
        );
    }

    /**
     * Sualı təsdiqləyir.
     */
    public Question approveQuestion(Long questionId) {

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Question not found with id: " + questionId
                        ));

        question.setModerationStatus(ModerationStatus.APPROVED);

        return questionRepository.save(question);
    }

    /**
     * Sualı rədd edir.
     */
    public Question rejectQuestion(Long questionId) {

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Question not found with id: " + questionId
                        ));

        question.setModerationStatus(ModerationStatus.REJECTED);

        return questionRepository.save(question);
    }

    /**
     * Moderation status-u dəyişdirir.
     */
    public Question updateStatus(Long questionId,
                                 ModerationStatus status) {

        Question question = questionRepository.findById(questionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Question not found with id: " + questionId
                        ));

        question.setModerationStatus(status);

        return questionRepository.save(question);
    }

}