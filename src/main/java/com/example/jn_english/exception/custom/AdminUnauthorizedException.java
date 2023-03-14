package com.example.jn_english.exception.custom;

import com.example.jn_english.exception.CustomException;
import com.example.jn_english.exception.error.ErrorCode;

public class AdminUnauthorizedException extends CustomException {
    public static final AdminUnauthorizedException EXCEPTION =
            new AdminUnauthorizedException();
    private AdminUnauthorizedException() {
        super(ErrorCode.ADMIN_UNAUTHORIZED);
    }
}
