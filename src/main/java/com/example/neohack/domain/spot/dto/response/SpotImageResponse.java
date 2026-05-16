package com.example.neohack.domain.spot.dto.response;

import com.example.neohack.domain.spot.entity.SpotImage;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SpotImageResponse {

    private String spotImageId;
    private String imageUrl;

    public static SpotImageResponse from(SpotImage spotImage) {
        return SpotImageResponse.builder()
                .spotImageId(spotImage.getSpotImageId())
                .imageUrl(spotImage.getImageUrl())
                .build();
    }
}
