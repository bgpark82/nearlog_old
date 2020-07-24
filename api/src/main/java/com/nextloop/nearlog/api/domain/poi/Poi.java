package com.nextloop.nearlog.api.domain.poi;

import com.nextloop.nearlog.api.domain.base.BaseTime;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@ToString
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Poi extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String image;
    private double latitude;
    private double longitude;
    private String userId;

    public static Poi of(PoiDTO.Request request, String uploadImage) {
        return Poi.builder()
                .name(request.getName())
                .image(uploadImage)
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .userId(request.getUserId())
                .build();
    }
}
