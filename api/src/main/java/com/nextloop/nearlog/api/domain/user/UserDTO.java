package com.nextloop.nearlog.api.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class UserDTO {

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
