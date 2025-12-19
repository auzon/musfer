package me.oguzhanozer.musfer.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import me.oguzhanozer.musfer.authservice.entity.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    public Optional<RefreshToken> findByToken(String token);
}
