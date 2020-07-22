package com.nextloop.nearlog.api.domain.hello;

import com.nextloop.nearlog.api.domain.exception.ApiException;
import com.nextloop.nearlog.api.domain.exception.ErrorCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Hello")
@RestController
@RequestMapping(("/api/v1"))
public class HelloController {

    @ApiOperation(value = "에러 메세지", notes = "에러 메세지를 생성한다")
    @GetMapping
    public String hello() {
        throw new ApiException(ErrorCode.SERVER_ERROR);
    }

}
