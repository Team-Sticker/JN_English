package com.example.jn_english.controller;

import com.example.jn_english.controller.dto.request.AdminRequest;
import com.example.jn_english.controller.dto.request.LoginRequest;
import com.example.jn_english.controller.dto.response.TokenResponse;
import com.example.jn_english.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody AdminRequest request) {
        authService.signUp(request);
    }

    @PostMapping("/login")
    public TokenResponse login(@RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @PutMapping("/refresh")
    public TokenResponse refresh(@RequestHeader(value = "Refresh") String refresh) {
        return authService.refresh(refresh);
    }
}
