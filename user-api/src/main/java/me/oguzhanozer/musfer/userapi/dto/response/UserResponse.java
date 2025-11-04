package me.oguzhanozer.musfer.userapi.dto.response;

public record UserResponse(
        Long id,
        String email,
        String username
) {}