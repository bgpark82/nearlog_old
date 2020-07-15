package com.nextloop.nearlog.domain.user;

import com.nextloop.nearlog.domain.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class UserController {

    private final UserRepository userRepository;

    @PostMapping("/user")
    public Response<User> save() {
        User user = User.builder()
                    .username("bgpark")
                    .password("1234")
                    .build();
        return Response.of(userRepository.save(user));
    }

    @GetMapping("/user")
    public Response<List<User>> findAll() {
        return Response.of(userRepository.findAll());
    }

}
