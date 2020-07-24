package com.nextloop.nearlog.api.domain.poi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PoiDTOTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    void convert_request_to_response() throws JsonProcessingException {
        PoiDTO.Request request = mapper.readValue("{\n" +
                "            \"name\":\"성산일출봉\",\n" +
                "            \"image\":\"base64\",\n" +
                "            \"type\":\"image/jpg\",\n" +
                "            \"latitude\":35.052156,\n" +
                "                \"longitude\":127.054831,\n" +
                "            \"userId\":\"bgpark82@gmail.com\"\n" +
                "        }", PoiDTO.Request.class);
        String uploadImage = "amazon_s3_image";
        Poi poi = Poi.of(request, uploadImage);
        System.out.println(poi);
        assertThat(poi.getName()).isEqualTo("성산일출봉");
        assertThat(poi.getLatitude()).isEqualTo(35.052156);
        assertThat(poi.getLongitude()).isEqualTo(127.054831);
    }
}