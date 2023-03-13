package com.example.jn_english.exception.custom;

import com.example.jn_english.exception.CustomException;
import com.example.jn_english.exception.error.ErrorCode;

public class PostNotFoundException extends CustomException {
    public static final PostNotFoundException EXCEPTION =
            new PostNotFoundException();
    private PostNotFoundException() {
        super(ErrorCode.POST_NOT_FOUND);
    }
}
