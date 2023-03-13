package com.example.jn_english.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class TokenResponse {
    private final String access_token;
    private final String refresh_token;

    @Builder
    public TokenResponse(String access_token, String refresh_token) {
        this.access_token = access_token;
        this.refresh_token = refresh_token;
    }
}
