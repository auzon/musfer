package me.oguzhanozer.musfer.authservice.service;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import me.oguzhanozer.musfer.authservice.dto.request.RefreshTokenRequest;
import me.oguzhanozer.musfer.authservice.dto.response.TokenResponse;
import me.oguzhanozer.musfer.authservice.entity.AuthUser;
import me.oguzhanozer.musfer.authservice.entity.RefreshToken;
import me.oguzhanozer.musfer.authservice.repository.AuthUserRepository;
import me.oguzhanozer.musfer.authservice.repository.RefreshTokenRepository;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final AuthUserRepository authUserRepository;
    private final JwtService jwtService;
    private final RefreshTokenRepository refreshTokenRepository;

    public TokenResponse refresh(RefreshTokenRequest refreshTokenRequest) {
        Claims claims;
        try {
            claims = jwtService.extractAllClaims(refreshTokenRequest.refreshToken());
        } catch (Exception e) {
            throw new RuntimeException("Invalid refresh token");
        }

        Long userId = Long.valueOf(claims.getSubject());

        AuthUser authUser = authUserRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        String accessToken = jwtService.generateAccessToken(authUser);
        String refreshToken = jwtService.generateRefreshToken(authUser);

        return new TokenResponse(accessToken, refreshToken);
    }

    public void revoke(String token) {
        RefreshToken refreshToken = refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token."));

        refreshToken.setRevoked(true);

        refreshTokenRepository.save(refreshToken);
    }
}
