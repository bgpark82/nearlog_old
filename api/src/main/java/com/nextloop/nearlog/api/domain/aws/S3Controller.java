package com.nextloop.nearlog.api.domain.aws;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Controller
public class S3Controller {

    private final S3Uploader s3Uploader;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/api/v1/upload")
    @ResponseBody
    public String upload(@RequestParam("data") MultipartFile multipartFile) throws IOException {
        return s3Uploader.upload(multipartFile, "static");
    };
}
