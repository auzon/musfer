package me.oguzhanozer.musfer.authservice.dto.response;

public record TokenResponse(
                String accessToken,
                String refreshToken) {
}
