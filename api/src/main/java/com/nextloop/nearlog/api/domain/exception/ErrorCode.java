package com.nextloop.nearlog.api.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    SERVER_ERROR(500, "서버 에러", 5000);

    private final int status;
    private final String message;
    private final int code;
}
