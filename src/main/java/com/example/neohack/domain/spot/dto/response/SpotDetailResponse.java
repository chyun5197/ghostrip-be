package com.example.neohack.domain.spot.dto.response;

import com.example.neohack.domain.spot.entity.Spot;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SpotDetailResponse {

    private String spotId;
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
                .spotImageList(spot.getSpotImages().stream()
                        .map(SpotImageResponse::from)
                        .toList())
                .kakaoPlace(KakaoPlaceResponse.from(spot.getKakaoPlace()))
                .relatedContentList(spot.getRelatedContents().stream()
                        .map(RelatedContentResponse::from)
                        .toList())
                .build();
    }
}
