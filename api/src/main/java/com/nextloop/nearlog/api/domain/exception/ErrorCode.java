package com.nextloop.nearlog.api.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    SERVER_ERROR(500, "서버 에러", 5000),
    USER_EXIST(418, "사용자가 이미 존재합니다", 4000),
    UNAUTHENTICATED(403, "인증되지 않은 사용자입니다", 4001),
    FILE_CONVERSION_ERROR(500, "MultipartFile -> File 전환에 실패했습니다", 5001);

    private final int status;
    private final String message;
    private final int code;
}
