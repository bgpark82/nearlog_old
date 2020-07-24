package com.nextloop.nearlog.api.domain.poi;

import com.nextloop.nearlog.api.domain.auth.CustomUserDetailsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PoiController.class)
class PoiControllerTest {

    @Autowired
    private MockMvc mvc;
    @MockBean
    private CustomUserDetailsService customUserDetailsService;
    @MockBean
    private PoiRepository poiRepository;

    @Test
    void find_all_by_email() throws Exception {
        mvc.perform(get("/api/v1/poi/list/bgpark82@gmail.com"))
                .andDo(print())
                .andExpect(status().isOk());

    }
}