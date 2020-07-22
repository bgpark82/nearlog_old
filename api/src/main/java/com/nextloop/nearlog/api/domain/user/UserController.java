package com.nextloop.nearlog.api.domain.user;

import com.nextloop.nearlog.api.domain.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "User")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserRepository userRepository;

    @ApiOperation(value = "회원 저장")
    @PostMapping("/user")
    public Response<User> save(@RequestBody User user) {
        return Response.of(userRepository.save(user));
    }

    @ApiOperation(value = "회원 조회")
    @GetMapping("/user")
    public Response<List<User>> findAll() {
        return Response.of(userRepository.findAll());
    }


}
