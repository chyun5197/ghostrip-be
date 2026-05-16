package com.example.neohack.domain.spot.dto.response;

import com.example.neohack.domain.spot.entity.Spot;
import lombok.Builder;
import lombok.Getter;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
@Builder
public class SpotDetailResponse {

    private Long spotId;
    private String name;
    private String description;
    private Double fearLevel;
    private String visitWarning;
    private Long viewCount;

    private List<SpotImageResponse> spotImageList;
    private KakaoPlaceResponse kakaoPlace;
    private List<RelatedContentResponse> relatedContentList;

    public static SpotDetailResponse from(Spot spot) {
        return SpotDetailResponse.builder()
                .spotId(spot.getSpotId())
                .name(spot.getName())
                .description(spot.getDescription())
                .fearLevel(spot.getFearLevel())
                .visitWarning(spot.getVisitWarning())
                .viewCount(spot.getViewCount())
                .spotImageList(Optional.ofNullable(spot.getSpotImages())
                        .orElse(Collections.emptyList()).stream()
                        .map(SpotImageResponse::from)
                        .toList())
                .kakaoPlace(spot.getKakaoPlace() != null ? KakaoPlaceResponse.from(spot.getKakaoPlace()) : null)
                .relatedContentList(Optional.ofNullable(spot.getRelatedContents())
                        .orElse(Collections.emptyList()).stream()
                        .map(RelatedContentResponse::from)
                        .toList())
                .build();
    }
}
