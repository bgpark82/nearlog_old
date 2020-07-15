package com.nextloop.nearlog.domain.hello;

import com.nextloop.nearlog.domain.exception.ApiException;
import com.nextloop.nearlog.domain.exception.ErrorCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        throw new ApiException(ErrorCode.SERVER_ERROR);
    }
}
