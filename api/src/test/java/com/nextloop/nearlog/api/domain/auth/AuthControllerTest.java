package com.nextloop.nearlog.api.domain.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nextloop.nearlog.api.domain.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private CustomUserDetailsService detailsService;
    @MockBean
    private AuthService authService;

    @Test
    void user_sign_up() throws Exception {
        User user = User.builder()
                .email("bgpark82@gmail.com")
                .password("1234")
                .username("bgpark")
                .role(User.Role.USER)
                .build();

        when(authService.save(any())).thenReturn(user);

        mvc.perform(post("/api/v1/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user)))
                .andDo(print())
                .andExpect(status().isOk());

        verify(authService).save(any());
    }


}