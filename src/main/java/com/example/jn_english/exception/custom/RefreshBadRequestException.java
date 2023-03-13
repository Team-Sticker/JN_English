package com.example.jn_english.exception.custom;

import com.example.jn_english.exception.CustomException;
import com.example.jn_english.exception.error.ErrorCode;

public class RefreshBadRequestException extends CustomException {
    public static final RefreshBadRequestException EXCEPTION =
            new RefreshBadRequestException();
    private RefreshBadRequestException() {
        super(ErrorCode.REFRESH_BAD_REQUEST);
    }
}
