package com.nextloop.nearlog.api.domain.aws;

import com.nextloop.nearlog.api.domain.exception.ApiException;
import com.nextloop.nearlog.api.domain.exception.ErrorCode;
import com.nextloop.nearlog.api.domain.poi.Poi;
import com.nextloop.nearlog.api.domain.poi.PoiDTO;
import com.nextloop.nearlog.api.domain.poi.PoiRepository;
import com.nextloop.nearlog.api.domain.response.Response;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class S3Controller {

    private final S3Uploader s3Uploader;
    private final PoiRepository poiRepository;
    private final static String tmpdir = System.getProperty("java.io.tmpdir");


    @PostMapping("/upload")
    public String upload(@RequestParam("data") MultipartFile multipartFile) throws IOException {
        System.out.println(multipartFile);
        String result = s3Uploader.upload(multipartFile, "static");
        System.out.println(result);
        return "hello from server with data";
    };

    // TODO : 리펙토링
    // TODO : validation 추가
    @PostMapping("/image/upload")
    public Response<Poi> upload(@RequestBody PoiDTO.Request poiRequest) throws IOException {
        String type = poiRequest.getType();
        String extension = type.substring(type.indexOf('/') + 1);
        String fileName = UUID.randomUUID().toString() + "." + extension;

        BufferedImage image;
        byte[] imageByte= Base64.decodeBase64(poiRequest.getImage());
        try ( ByteArrayInputStream io = new ByteArrayInputStream(imageByte)){
            image = ImageIO.read(io);
            int height = image.getHeight();
            int width = image.getWidth();
        } catch(Exception e) {
            throw new ApiException(ErrorCode.FILE_CONVERSION_ERROR);
        }
        File file = new File(tmpdir, fileName);
        ImageIO.write(image, "jpg", file);
        String uploadFile = s3Uploader.upload(file, "static");

        Poi poi = Poi.of(poiRequest, uploadFile);
        poiRepository.save(poi);

        return Response.of(poi);
    }
}
