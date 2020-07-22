package com.nextloop.nearlog.api.domain.auth;

import com.nextloop.nearlog.api.domain.user.UserPrincipal;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class JwtProviderTest {

    @Test
    void create_jwt() {
        UserPrincipal user = UserPrincipal.builder()
                .id(1L)
                .username("bgpark")
                .email("bgpark82@gmail.com")
                .build();
        JwtProvider jwtProvider = new JwtProvider();
        ReflectionTestUtils.setField(jwtProvider, "jwtSecret","test");

        String token = jwtProvider.createToken(user);
        assertThat(token).contains(".");
    }

    @Test
    void get_expiry_time() {
        JwtProvider jwtProvider = new JwtProvider();

        ReflectionTestUtils.setField(jwtProvider, "expiryDates",30);

        Date expiryTime = jwtProvider.getExpiryTime();
        System.out.println(expiryTime);
        assertThat(expiryTime).isNotNull();
    }
}