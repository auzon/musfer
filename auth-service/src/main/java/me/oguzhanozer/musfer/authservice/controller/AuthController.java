package me.oguzhanozer.musfer.authservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import me.oguzhanozer.musfer.authservice.dto.request.LoginRequest;
import me.oguzhanozer.musfer.authservice.dto.request.LogoutRequest;
import me.oguzhanozer.musfer.authservice.dto.request.RefreshTokenRequest;
import me.oguzhanozer.musfer.authservice.dto.request.RegisterRequest;
import me.oguzhanozer.musfer.authservice.dto.response.AuthUserResponse;
import me.oguzhanozer.musfer.authservice.dto.response.TokenResponse;
import me.oguzhanozer.musfer.authservice.service.LoginService;
import me.oguzhanozer.musfer.authservice.service.RefreshTokenService;
import me.oguzhanozer.musfer.authservice.service.RegistrationService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegistrationService registrationService;
    private final LoginService loginService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    public AuthUserResponse register(@RequestBody RegisterRequest registerRequest) {
        return registrationService.register(registerRequest);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest loginRequest) {
        return loginService.login(loginRequest);
    }

    @PostMapping("/refresh")
    public TokenResponse refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return refreshTokenService.refresh(refreshTokenRequest);
    }

    @PostMapping("/revoke")
    public ResponseEntity<?> revoke(@RequestBody LogoutRequest logoutRequest) {
        refreshTokenService.revoke(logoutRequest.refreshToken());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/test")
    public String test() {
        return "return from test endpoint";
    }

}
