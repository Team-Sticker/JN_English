package com.example.jn_english.exception.custom;

import com.example.jn_english.exception.CustomException;
import com.example.jn_english.exception.error.ErrorCode;

public class ImageNotSaveException extends CustomException {
    public static final ImageNotSaveException EXCEPTION =
            new ImageNotSaveException();
    private ImageNotSaveException() {
        super(ErrorCode.IMAGE_NOT_SAVE);
    }
}
