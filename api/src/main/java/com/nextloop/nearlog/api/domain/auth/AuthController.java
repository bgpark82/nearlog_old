package com.nextloop.nearlog.api.domain.auth;

import com.nextloop.nearlog.api.domain.response.Response;
import com.nextloop.nearlog.api.domain.user.User;
import com.nextloop.nearlog.api.domain.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public Response<User> register(@RequestBody UserDTO.SignUp user) {
        return Response.of(authService.save(user));
    }

}
