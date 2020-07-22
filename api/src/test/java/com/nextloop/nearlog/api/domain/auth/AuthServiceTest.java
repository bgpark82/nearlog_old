package com.nextloop.nearlog.api.domain.auth;

import com.nextloop.nearlog.api.domain.exception.ApiException;
import com.nextloop.nearlog.api.domain.user.User;
import com.nextloop.nearlog.api.domain.user.UserDTO;
import com.nextloop.nearlog.api.domain.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class AuthServiceTest {

    private AuthService authService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        authService = new AuthService(userRepository, passwordEncoder);
    }

    @Test
    void throw_error_when_user_exist() {
        Optional<User> savedUser = Optional.of(
                User.builder()
                        .email("bgpark82@gmail.com")
                        .build());

        when(userRepository.findByEmail(any())).thenReturn(savedUser);

        UserDTO.SignUp signUpUser = new UserDTO.SignUp();
        signUpUser.setEmail("bgpark82@gmail.com");
        assertThatThrownBy(() -> { authService.save(signUpUser); })
                .isInstanceOf(ApiException.class);

    }
}