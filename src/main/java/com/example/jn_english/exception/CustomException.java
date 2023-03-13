package com.example.jn_english.exception;

import com.example.jn_english.exception.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException{
    private final ErrorCode errorCode;
}
