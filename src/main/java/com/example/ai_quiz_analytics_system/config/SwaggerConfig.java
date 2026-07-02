package com.example.ai_quiz_analytics_system.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AI Quiz Analytics System")
                        .version("1.0")
                        .description("AI Quiz + Analytics + WebSocket System API"));
    }
}