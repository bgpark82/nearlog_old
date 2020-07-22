package com.nextloop.nearlog.api.domain.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private UserRepository userRepository;

    @Test
    void create_user() throws Exception {
        User user = User.builder()
                .email("bgpark82@gmail.com")
                .password("1234")
                .role(User.Role.ADMIN)
                .build();

        mvc.perform(post("/api/v1/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(user)))
                .andDo(print())
                .andExpect(status().isCreated());

        verify(userRepository).save(user);
    }
}