package com.example.ai_quiz_analytics_system.quiz.service;

import com.example.ai_quiz_analytics_system.common.exception.ResourceNotFoundException;
import com.example.ai_quiz_analytics_system.quiz.dto.GenerateQuizRequest;
import com.example.ai_quiz_analytics_system.quiz.dto.QuestionResponse;
import com.example.ai_quiz_analytics_system.quiz.dto.QuizResponse;
import com.example.ai_quiz_analytics_system.quiz.entity.Question;
import com.example.ai_quiz_analytics_system.quiz.entity.Quiz;
import com.example.ai_quiz_analytics_system.quiz.mapper.QuestionMapper;
import com.example.ai_quiz_analytics_system.quiz.repository.QuestionRepository;
import com.example.ai_quiz_analytics_system.quiz.repository.QuizRepository;
import com.example.ai_quiz_analytics_system.quiz.validator.QuizRequestValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuestionRepository questionRepository;
    private final QuestionGenerationService questionGenerationService;
    private final QuestionMapper questionMapper;
    private final QuizRequestValidator validator;

    public QuizService(QuizRepository quizRepository,
                       QuestionRepository questionRepository,
                       QuestionGenerationService questionGenerationService,
                       QuestionMapper questionMapper,
                       QuizRequestValidator validator) {

        this.quizRepository = quizRepository;
        this.questionRepository = questionRepository;
        this.questionGenerationService = questionGenerationService;
        this.questionMapper = questionMapper;
        this.validator = validator;
    }

    /**
     * Generate new quiz using AI
     */
    public QuizResponse generateQuiz(GenerateQuizRequest request) {

        validator.validate(request);

        Quiz quiz = new Quiz();
        quiz.setTopic(request.getTopic());
        quiz.setDifficulty(request.getDifficulty());

        List<Question> generatedQuestions =
                questionGenerationService.generateQuestions(request);

        generatedQuestions.forEach(quiz::addQuestion);

        Quiz savedQuiz = quizRepository.save(quiz);

        return convertToResponse(savedQuiz);
    }

    /**
     * Get quiz by id
     */
    @Transactional(readOnly = true)
    public QuizResponse getQuiz(Long id) {

        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Quiz not found."));

        return convertToResponse(quiz);
    }

    /**
     * Get all quizzes
     */
    @Transactional(readOnly = true)
    public List<QuizResponse> getAllQuizzes() {

        return quizRepository.findAll()
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Delete quiz
     */
    public void deleteQuiz(Long id) {

        Quiz quiz = quizRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Quiz not found."));

        quizRepository.delete(quiz);
    }
    /**
     * Search quizzes by topic
     */
    @Transactional(readOnly = true)
    public List<QuizResponse> searchByTopic(String topic) {

        return quizRepository.findByTopicContainingIgnoreCase(topic)
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    /**
     * Search quizzes by difficulty
     */
    @Transactional(readOnly = true)
    public List<QuizResponse> searchByDifficulty(String difficulty) {

        return quizRepository.findByDifficulty(
                        com.example.ai_quiz_analytics_system.quiz.entity.Difficulty
                                .valueOf(difficulty.toUpperCase())
                )
                .stream()
                .map(this::convertToResponse)
                .toList();
    }

    /**
     * Convert Entity -> DTO
     */
    private QuizResponse convertToResponse(Quiz quiz) {

        QuizResponse response = new QuizResponse();

        response.setId(quiz.getId());
        response.setTopic(quiz.getTopic());
        response.setDifficulty(quiz.getDifficulty().name());
        response.setCreatedAt(quiz.getCreatedAt());

        response.setQuestions(mapQuestions(quiz.getQuestions()));

        return response;
    }

    /**
     * Convert Question List -> DTO List
     */
    private List<QuestionResponse> mapQuestions(List<Question> questions) {

        return questions.stream()
                .map(questionMapper::toResponse)
                .toList();
    }

}