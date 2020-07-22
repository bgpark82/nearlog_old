package com.nextloop.nearlog.api.domain.response;

import java.time.LocalDateTime;

public class TypeResponse<T> implements Response<T> {

     int status;
     T data;
     LocalDateTime timestamp;

    public TypeResponse(int status, T data) {
        this.status = status;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public LocalDateTime getTimestamp() {
        return timestamp = LocalDateTime.now();
    }
}
