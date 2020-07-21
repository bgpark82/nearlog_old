package com.nextloop.nearlog.domain.user;

import com.nextloop.nearlog.domain.response.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "User")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserRepository userRepository;

    @ApiOperation(value = "회원 저장")
    @PostMapping("/user")
    public Response<User> save() {
        User user = User.builder()
                    .name("bgpark")
                    .password("1234")
                    .build();
        return Response.of(userRepository.save(user));
    }

    @ApiOperation(value = "회원 조회")
    @GetMapping("/user")
    public Response<List<User>> findAll() {
        return Response.of(userRepository.findAll());
    }

}
