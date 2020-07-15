package com.nextloop.nearlog.domain.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    SERVER_ERROR(500, "서버 에러", 5000);

    private final int status;
    private final String message;
    private final int code;

    ErrorCode(int status, String message, int code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }
}
