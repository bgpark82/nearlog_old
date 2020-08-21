package com.nextloop.nearlog.api.domain.poi;

import com.nextloop.nearlog.api.domain.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/poi")
@RequiredArgsConstructor
public class PoiController {

    private final PoiRepository poiRepository;

    @GetMapping("/list/{email}")
    public Response<List<Poi>> findAllByUserId(@PathVariable String email) {
        return Response.of(poiRepository.findAllByUserIdAndCreatedDateAfter(email, LocalDateTime.now().minusDays(15)));
    }
}
