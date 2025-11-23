package me.oguzhanozer.musfer.userservice.dto.response;

public record UserResponse(
                Long id,
                String email,
                String username) {
}