package com.nextloop.nearlog.api.domain.auth;

import com.nextloop.nearlog.api.domain.exception.ApiException;
import com.nextloop.nearlog.api.domain.exception.ErrorCode;
import com.nextloop.nearlog.api.domain.user.User;
import com.nextloop.nearlog.api.domain.user.UserDTO;
import com.nextloop.nearlog.api.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User save(UserDTO.SignUp user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user.toEntity());
    }
}
