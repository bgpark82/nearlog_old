package com.nextloop.nearlog.api.domain.auth;

import com.nextloop.nearlog.api.domain.response.Response;
import com.nextloop.nearlog.api.domain.user.User;
import com.nextloop.nearlog.api.domain.user.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/signup")
    public Response<User> signup(@RequestBody UserDTO.SignUp user) {
        return Response.of(authService.save(user));
    }

    @PostMapping("/signin")
    public Response<Map> singin(@RequestBody UserDTO.SignIn user) {
        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        Map<String, String> map = new HashMap<>();
        map.put("token","this is jwt token");
        return Response.of(map);
    }

}
