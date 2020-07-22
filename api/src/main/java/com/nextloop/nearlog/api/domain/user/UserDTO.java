package com.nextloop.nearlog.api.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

@RequiredArgsConstructor
public class UserDTO {

    private final PasswordEncoder passwordEncoder;

    @Getter
    @Setter
    public static class SignUp {

        private String email;
        private String username;
        private String password;
        private String profile;

        public User toEntity() {
            return User.builder()
                    .email(email)
                    .username(username)
                    .password(password)
                    .profile(profile)
                    .role(User.Role.USER)
                    .build();
        }
    }
}
