package com.example.ai_quiz_analytics_system.auth.service;

import com.example.ai_quiz_analytics_system.auth.entity.RefreshToken;
import com.example.ai_quiz_analytics_system.auth.entity.User;
import com.example.ai_quiz_analytics_system.auth.repository.RefreshTokenRepository;
import com.example.ai_quiz_analytics_system.auth.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    // refresh token validity (1 day)
    private final long refreshTokenDuration = 24 * 60 * 60;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository,
                               UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    /**
     * Create new refresh token for user
     */
    public RefreshToken createRefreshToken(User user) {

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusSeconds(refreshTokenDuration));

        return refreshTokenRepository.save(refreshToken);
    }

    /**
     * Find refresh token
     */
    public RefreshToken findByToken(String token) {
        return refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));
    }

    /**
     * Validate token expiration
     */
    public RefreshToken verifyExpiration(RefreshToken token) {

        if (token.getExpiryDate().isBefore(Instant.now())) {
            refreshTokenRepository.delete(token);
            throw new RuntimeException("Refresh token expired");
        }

        return token;
    }

    /**
     * Delete refresh token (logout)
     */
    public void deleteByUser(User user) {
        refreshTokenRepository.deleteAll();
    }

    /**
     * Optional: rotate refresh token (best practice)
     */
    public RefreshToken rotateToken(RefreshToken oldToken) {

        RefreshToken newToken = new RefreshToken();
        newToken.setUser(oldToken.getUser());
        newToken.setToken(UUID.randomUUID().toString());
        newToken.setExpiryDate(Instant.now().plusSeconds(refreshTokenDuration));

        refreshTokenRepository.delete(oldToken);
        return refreshTokenRepository.save(newToken);
    }
}