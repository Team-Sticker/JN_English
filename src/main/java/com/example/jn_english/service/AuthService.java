package com.example.jn_english.service;

import com.example.jn_english.controller.dto.request.AdminRequest;
import com.example.jn_english.controller.dto.request.LoginRequest;
import com.example.jn_english.controller.dto.response.TokenResponse;
import com.example.jn_english.entity.admin.Admin;
import com.example.jn_english.entity.admin.AdminRepository;
import com.example.jn_english.entity.refresh.Refresh;
import com.example.jn_english.entity.refresh.RefreshRepository;
import com.example.jn_english.exception.custom.AccountIdNotFoundException;
import com.example.jn_english.exception.custom.AdminUnauthorizedException;
import com.example.jn_english.exception.custom.PasswordWrongException;
import com.example.jn_english.exception.custom.RefreshBadRequestException;
import com.example.jn_english.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AdminRepository adminRepository;

    private final PasswordEncoder getEncoder;

    private final RefreshRepository refreshRepository;

    private final TokenProvider tokenProvider;

    public void signUp(AdminRequest request) {
        if(adminRepository.findByAccountId(request.getAccountId()).isPresent()) {
            throw AccountIdNotFoundException.EXCEPTION;
        }
        adminRepository.save(Admin.builder()
                        .name(request.getName())
                        .accountId(request.getAccountId())
                        .password(getEncoder.encode(request.getPassword()))
                .build());
    }

    public TokenResponse login(LoginRequest request) {
        Admin admin = adminRepository.findByAccountId(request.getAccountId())
                .orElseThrow(()->AccountIdNotFoundException.EXCEPTION);
        if (!getEncoder.matches(request.getPassword(), admin.getPassword())) {
            throw PasswordWrongException.EXCEPTION;
        }
        return TokenResponse.builder()
                .access_token(tokenProvider.accessToken(request.getAccountId()))
                .refresh_token(tokenProvider.refreshToken(request.getAccountId()))
                .build();
    }

    public TokenResponse refresh(String refresh) {
        if (!tokenProvider.isRefresh(refresh)) {
            throw RefreshBadRequestException.EXCEPTION;
        }
        String accountId = tokenProvider.parseToken(refresh).getSubject();
        Admin admin = adminRepository.findByAccountId(accountId).orElseThrow(()->AccountIdNotFoundException.EXCEPTION);
        Refresh refreshObj = refreshRepository.findByAdmin(admin).orElseThrow(()-> AdminUnauthorizedException.EXCEPTION);
        return TokenResponse.builder()
                .access_token(tokenProvider.accessToken(accountId))
                .refresh_token(refreshRepository.save(refreshObj.updateRefresh(tokenProvider.refreshToken(accountId))).getRefresh())
                .build();
    }
}
