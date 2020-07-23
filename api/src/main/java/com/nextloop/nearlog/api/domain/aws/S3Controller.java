package com.nextloop.nearlog.api.domain.aws;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class S3Controller {

    private final S3Uploader s3Uploader;

    @PostMapping("/upload")
    public String upload(@RequestParam("data") MultipartFile multipartFile) throws IOException {
        return s3Uploader.upload(multipartFile, "static");
    };

    @PostMapping("/native")
    public String upload(@RequestBody Place place) {
        System.out.println(place);
        return "message from server";
    }
}
