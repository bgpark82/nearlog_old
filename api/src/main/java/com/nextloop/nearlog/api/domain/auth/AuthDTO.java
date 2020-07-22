package com.nextloop.nearlog.api.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AuthDTO {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Response {

        private String token;

        public static AuthDTO.Response of(String token) {
            return new AuthDTO.Response(token);
        }
    }
}
