package com.example.jn_english.exception.custom;

import com.example.jn_english.exception.CustomException;
import com.example.jn_english.exception.error.ErrorCode;

public class ImageBadRequestException extends CustomException {

    public static final ImageBadRequestException EXCEPTION =
            new ImageBadRequestException();

    private ImageBadRequestException() {
        super(ErrorCode.IMAGE_BAD_REQUEST);
    }
}
