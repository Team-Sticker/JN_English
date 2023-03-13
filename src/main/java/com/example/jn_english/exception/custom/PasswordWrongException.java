package com.example.jn_english.exception.custom;

import com.example.jn_english.exception.CustomException;
import com.example.jn_english.exception.error.ErrorCode;

public class PasswordWrongException extends CustomException {

    public static final PasswordWrongException EXCEPTION =
            new PasswordWrongException();

    private PasswordWrongException() {
        super(ErrorCode.PASSWORD_CONFLICT);
    }
}
