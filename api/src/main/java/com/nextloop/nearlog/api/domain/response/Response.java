package com.nextloop.nearlog.api.domain.response;

import java.time.LocalDateTime;

public interface Response<T> {

    static <T> Response<T> of(int status, T data) {
        return new TypeResponse<>(status, data);
    }

    static <T> Response<T> of(T data) {
        return new TypeResponse<>(200, data);
    }

    int getStatus();

    T getData();

    LocalDateTime getTimestamp();
}
