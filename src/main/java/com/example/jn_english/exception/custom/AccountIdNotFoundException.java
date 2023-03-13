package com.example.jn_english.exception.custom;

import com.example.jn_english.exception.CustomException;
import com.example.jn_english.exception.error.ErrorCode;

public class AccountIdNotFoundException extends CustomException {
    public static final CustomException EXCEPTION = new AccountIdNotFoundException();
    private AccountIdNotFoundException() {
        super(ErrorCode.ACCOUNT_ID_NOT_FOUND);
    }
}
