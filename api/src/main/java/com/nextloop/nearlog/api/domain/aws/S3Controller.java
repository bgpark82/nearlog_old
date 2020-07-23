package com.nextloop.nearlog.api.domain.aws;

import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class S3Controller {

    private final S3Uploader s3Uploader;



    @PostMapping("/upload")
    public String upload(@RequestParam("data") MultipartFile multipartFile) throws IOException {
        System.out.println(multipartFile);
        String result = s3Uploader.upload(multipartFile, "static");
        System.out.println(result);
        return "hello from server with data";
    };

    @PostMapping("/native")
    public String upload(@RequestBody Place place) {
        System.out.println(place);
        return "message from server";
    }

    @PostMapping("/base")
    public String uploadBase64(@RequestBody UploadFile uploadFile) throws IOException {

        // TODO : type에서 확장자 가져오기, naming convenstion 찾기
        String name = "image:" + LocalDateTime.now().toString() + ".jpg";
        BufferedImage image;
        byte[] imageByte= Base64.decodeBase64(uploadFile.getUploadFile());
        try ( ByteArrayInputStream io = new ByteArrayInputStream(imageByte)){
            image = ImageIO.read(io);
        } catch(Exception e) {
            return "server error : "+ e;
        }
        ImageIO.write(image, "jpg", new File(name));
        File file = ResourceUtils.getFile(name);
        return s3Uploader.upload(file, "static");

    }
}
