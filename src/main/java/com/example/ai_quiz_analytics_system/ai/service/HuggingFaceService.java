package com.example.ai_quiz_analytics_system.ai.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class HuggingFaceService {

    private final WebClient webClient;

    @Value("${huggingface.api.token}")
    private String token;

    @Value("${huggingface.api.url}")
    private String url;

    public HuggingFaceService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String generate(String prompt) {

        return webClient.post()
                .uri(url)
                .header("Authorization", "Bearer " + token)
                .bodyValue(Map.of("inputs", prompt))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}