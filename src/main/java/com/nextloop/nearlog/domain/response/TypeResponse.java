package com.nextloop.nearlog.domain.response;

import java.time.LocalDateTime;

public class TypeResponse<T> implements Response<T> {

    private String status;
    private T data;
    private LocalDateTime timestamp;

    public TypeResponse(String status, T data) {
        this.status = status;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String getStatus() {
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
