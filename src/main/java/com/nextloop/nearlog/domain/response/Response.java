package com.nextloop.nearlog.domain.response;

import java.time.LocalDateTime;

public interface Response<T> {

    static <T> Response<T> of(String code, T data) {
        return new TypeResponse<>(code, data);
    }

    static <T> Response<T> of(T data) {
        return  new TypeResponse<>("200", data);
    }

    String getStatus();

    T getData();

    LocalDateTime getTimestamp();
}
