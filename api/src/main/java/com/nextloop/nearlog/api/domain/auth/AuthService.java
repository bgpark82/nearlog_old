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
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Transactional
    public User save(UserDTO.SignUp user) {
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            throw new ApiException(ErrorCode.USER_EXIST);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user.toEntity());
    }

    @Transactional
    public AuthDTO.Response signIn(UserDTO.SignIn user) {
        Authentication authenticate;
        try {
            authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            user.getPassword()
                    ));
            SecurityContextHolder.getContext().setAuthentication(authenticate);
        } catch(RuntimeException e) {
            throw new ApiException(ErrorCode.UNAUTHENTICATED);
        }
        // TODO : JWT 생성
        return AuthDTO.Response.of(jwtProvider.generateToken(authenticate));
    }
}
