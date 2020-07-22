package com.nextloop.nearlog.api.domain.exception;

import com.nextloop.nearlog.api.domain.response.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionController {

    @ExceptionHandler(RuntimeException.class)
    protected Response<ErrorResponse> handleRuntimeException(ApiException apiException) {
        return Response.of(
                apiException.getStatus(),
                ErrorResponse.of(apiException.getErrorCode()));
    }
}
