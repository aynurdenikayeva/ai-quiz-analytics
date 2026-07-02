package com.example.ai_quiz_analytics_system.auth.mapper;

import com.example.ai_quiz_analytics_system.auth.dto.RegisterRequest;
import com.example.ai_quiz_analytics_system.auth.entity.User;

public class UserMapper {

    public static User toEntity(RegisterRequest req) {
        User user = new User();
        user.setUsername(req.username);
        user.setEmail(req.email);
        return user;
    }
}
