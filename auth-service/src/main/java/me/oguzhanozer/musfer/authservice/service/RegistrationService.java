package me.oguzhanozer.musfer.authservice.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import me.oguzhanozer.musfer.authservice.entity.AuthUser;
import me.oguzhanozer.musfer.authservice.dto.request.RegisterRequest;
import me.oguzhanozer.musfer.authservice.dto.response.AuthUserResponse;
import me.oguzhanozer.musfer.authservice.repository.AuthUserRepository;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final AuthUserRepository authUserRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthUserResponse register(RegisterRequest registerRequest) {
        if (authUserRepository.existsByUsername(registerRequest.username())) {
            throw new RuntimeException("Username is already in use");
        }

        AuthUser newUser = new AuthUser();
        newUser.setUsername(registerRequest.username());
        newUser.setPassword(passwordEncoder.encode(registerRequest.password()));

        AuthUser savedUser = authUserRepository.save(newUser);
        return new AuthUserResponse(savedUser.getId(), savedUser.getUsername());
    }

}
