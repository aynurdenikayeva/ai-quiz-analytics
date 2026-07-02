package com.example.ai_quiz_analytics_system.auth.service;
import com.example.ai_quiz_analytics_system.auth.dto.AuthResponse;
import com.example.ai_quiz_analytics_system.auth.dto.LoginRequest;
import com.example.ai_quiz_analytics_system.auth.dto.RefreshTokenRequest;
import com.example.ai_quiz_analytics_system.auth.dto.RegisterRequest;
import com.example.ai_quiz_analytics_system.auth.entity.RefreshToken;
import com.example.ai_quiz_analytics_system.auth.entity.Role;
import com.example.ai_quiz_analytics_system.auth.entity.User;
import com.example.ai_quiz_analytics_system.auth.repository.RefreshTokenRepository;
import com.example.ai_quiz_analytics_system.auth.repository.UserRepository;
import com.example.ai_quiz_analytics_system.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshRepo;
    private final JwtService jwtService;
    private final PasswordEncoder encoder;

    public AuthService(UserRepository userRepository,
                       RefreshTokenRepository refreshRepo,
                       JwtService jwtService,
                       PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.refreshRepo = refreshRepo;
        this.jwtService = jwtService;
        this.encoder = encoder;
    }

    public AuthResponse register(RegisterRequest request) {

        User user = new User();
        user.setUsername(request.username);
        user.setPassword(encoder.encode(request.password));
        user.setEmail(request.email);
        user.setRole(Role.USER);

        userRepository.save(user);

        String accessToken = jwtService.generateToken(user);

        RefreshToken refresh = createRefreshToken(user);

        return new AuthResponse(accessToken, refresh.getToken(), user.getRole().name());
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByUsername(request.username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(request.password, user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String accessToken = jwtService.generateToken(user);


        RefreshToken refresh = createRefreshToken(user);

        return new AuthResponse(accessToken, refresh.getToken(), user.getRole().name());
    }

    public AuthResponse refreshToken(RefreshTokenRequest request) {

        RefreshToken token = refreshRepo.findByToken(request.refreshToken)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (token.getExpiryDate().isBefore(Instant.now())) {
            throw new RuntimeException("Refresh token expired");
        }

        User user = token.getUser();
        String newAccessToken = jwtService.generateToken(user);

        return new AuthResponse(
                newAccessToken,
                token.getToken(),
                user.getRole().name()
        );
    }

    private RefreshToken createRefreshToken(User user) {

        RefreshToken token = new RefreshToken();
        token.setUser(user);
        token.setToken(java.util.UUID.randomUUID().toString());
        token.setExpiryDate(Instant.now().plusSeconds(86400));

        return refreshRepo.save(token);
    }
}