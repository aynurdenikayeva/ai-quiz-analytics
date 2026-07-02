package com.example.ai_quiz_analytics_system.quiz.controller;

import com.example.ai_quiz_analytics_system.quiz.dto.GenerateQuizRequest;
import com.example.ai_quiz_analytics_system.quiz.dto.QuizResponse;
import com.example.ai_quiz_analytics_system.quiz.entity.Question;
import com.example.ai_quiz_analytics_system.quiz.service.QuizModerationService;
import com.example.ai_quiz_analytics_system.quiz.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;
    private final QuizModerationService moderationService;

    public QuizController(QuizService quizService,
                          QuizModerationService moderationService) {
        this.quizService = quizService;
        this.moderationService = moderationService;
    }

    /**
     * AI vasitəsilə yeni quiz yarat
     */
    @PostMapping("/generate")
    @ResponseStatus(HttpStatus.CREATED)
    public QuizResponse generateQuiz(
            @RequestBody GenerateQuizRequest request) {

        return quizService.generateQuiz(request);
    }

    /**
     * Bütün quizlər
     */
    @GetMapping
    public List<QuizResponse> getAllQuizzes() {
        return quizService.getAllQuizzes();
    }

    /**
     * Id-yə görə quiz
     */
    @GetMapping("/{id}")
    public QuizResponse getQuiz(@PathVariable Long id) {
        return quizService.getQuiz(id);
    }

    /**
     * Mövzuya görə axtarış
     */
    @GetMapping("/topic/{topic}")
    public List<QuizResponse> searchByTopic(
            @PathVariable String topic) {

        return quizService.searchByTopic(topic);
    }

    /**
     * Çətinlik səviyyəsinə görə axtarış
     */
    @GetMapping("/difficulty/{difficulty}")
    public List<QuizResponse> searchByDifficulty(
            @PathVariable String difficulty) {

        return quizService.searchByDifficulty(difficulty);
    }

    /**
     * Quiz sil
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteQuiz(@PathVariable Long id) {
        quizService.deleteQuiz(id);
    }

    /**
     * Moderation gözləyən suallar
     */
    @GetMapping("/moderation/pending")
    public List<Question> getPendingQuestions() {
        return moderationService.getPendingQuestions();
    }

    /**
     * Sualı təsdiqlə
     */
    @PutMapping("/moderation/{id}/approve")
    public Question approveQuestion(@PathVariable Long id) {
        return moderationService.approveQuestion(id);
    }

    /**
     * Sualı rədd et
     */
    @PutMapping("/moderation/{id}/reject")
    public Question rejectQuestion(@PathVariable Long id) {
        return moderationService.rejectQuestion(id);
    }
}