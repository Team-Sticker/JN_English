package com.example.jn_english.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    ACCOUNT_ID_NOT_FOUND(404, "아이디를 찾을 수 없습니다."),
    PASSWORD_CONFLICT(409, "비밀번호가 일치하지 않습니다."),
    IMAGE_NOT_SAVE(400, "사진이 저장되지 않았습니다."),
    IMAGE_BAD_REQUEST(400, "이미지가 올바르지 않습니다."),
    POST_NOT_FOUND(404, "게시물을 찾을 수 없습니다."),
    REFRESH_BAD_REQUEST(400, "Refresh Token이 아닙니다."),
    ADMON_UNAUTHORIZED(401, "로그인 되지 않은 사용자입니다.");
    private final int status;

    private final String message;
}
