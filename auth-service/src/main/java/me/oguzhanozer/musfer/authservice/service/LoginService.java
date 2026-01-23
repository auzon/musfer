package me.oguzhanozer.musfer.authservice.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.oguzhanozer.musfer.authservice.dto.request.LoginRequest;
import me.oguzhanozer.musfer.authservice.dto.response.TokenResponse;
import me.oguzhanozer.musfer.authservice.entity.AuthUser;
import me.oguzhanozer.musfer.authservice.enums.AuthUserStatus;
import me.oguzhanozer.musfer.authservice.repository.AuthUserRepository;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;

    public TokenResponse login(LoginRequest loginRequest) {
        AuthUser user = authUserRepository.findByUsername(loginRequest.username())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));

        if (!passwordEncoder.matches(loginRequest.password(), user.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        if (user.getStatus() != AuthUserStatus.ACTIVE) {
            throw new RuntimeException("Only active users can login");
        }

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        refreshTokenService.save(refreshToken);

        return new TokenResponse(accessToken, refreshToken);
    }
}
