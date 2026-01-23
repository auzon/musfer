package me.oguzhanozer.musfer.authservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import me.oguzhanozer.musfer.authservice.entity.AuthUser;

public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {
    public boolean existsByUsername(String username);

    public Optional<AuthUser> findByUsername(String username);
}
