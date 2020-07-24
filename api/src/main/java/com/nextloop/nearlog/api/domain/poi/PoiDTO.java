package com.nextloop.nearlog.api.domain.poi;

import lombok.Builder;
import lombok.Getter;

public class PoiDTO {

    @Getter
    public static class Request {
        private String name;
        private String image;
        private String type;
        private double latitude;
        private double longitude;
        private String userId;
    }
}
