package com.nextloop.nearlog.api.domain.auth;

import com.nextloop.nearlog.api.domain.exception.ApiException;
import com.nextloop.nearlog.api.domain.exception.ErrorCode;
import com.nextloop.nearlog.api.domain.response.Response;
import com.nextloop.nearlog.api.domain.user.User;
import com.nextloop.nearlog.api.domain.user.UserDTO;
import com.nextloop.nearlog.api.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public User save(UserDTO.SignUp user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new ApiException(ErrorCode.USER_EXIST);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user.toEntity());
    }

    public Map<String, String> signIn(UserDTO.SignIn user) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            user.getPassword()
                    ));
            SecurityContextHolder.getContext().setAuthentication(authenticate);
        } catch(RuntimeException e) {
            throw new ApiException(ErrorCode.UNAUTHENTICATED);
        }
        Map<String, String> map = new HashMap<>();
        map.put("token","this is jwt token");
        return map;

    }
}
