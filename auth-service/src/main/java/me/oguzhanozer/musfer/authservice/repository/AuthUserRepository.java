package me.oguzhanozer.musfer.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import me.oguzhanozer.musfer.authservice.entity.AuthUser;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    public boolean existsByEmail(String email);

    public Optional<AuthUser> findByEmail(String email);
}
