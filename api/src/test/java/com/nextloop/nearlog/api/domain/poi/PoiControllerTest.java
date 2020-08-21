package com.nextloop.nearlog.api.domain.poi;

import com.nextloop.nearlog.api.domain.auth.CustomUserDetailsService;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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
        List<Poi> list = Arrays.asList(
                Poi.builder().id(1L).userId("bgpark82@gmail.com").build(),
                Poi.builder().id(2L).userId("bgpark82@gmail.com").build(),
                Poi.builder().id(3L).userId("kassjd@gmail.com").build()
        );
        when(poiRepository.findAllByUserIdAndCreatedDateAfter(any(),any()))
                .thenReturn(list);

        mvc.perform(get("/api/v1/poi/list/bgpark82@gmail.com"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isNotEmpty());
    }
}