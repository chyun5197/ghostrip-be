package com.example.neohack.domain.spot.dto;

import com.example.neohack.domain.spot.entity.KakaoPlace;
import com.example.neohack.domain.spot.entity.RelatedContent;
import com.example.neohack.domain.spot.entity.Spot;
import com.example.neohack.domain.spot.entity.SpotImage;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class SpotDetailResponse {

    private Long spotId;
    private String name;
    private String description;
    private Double fearLevel;
    private String visitWarning;
    private Long viewCount;
    private List<String> imageUrlList;

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
                .imageUrlList(spot.getSpotImages().stream()
                        .map(SpotImage::getImageUrl)
                        .toList())
                .kakaoPlace(KakaoPlaceResponse.from(spot.getKakaoPlace()))
                .relatedContentList(spot.getRelatedContents().stream()
                        .map(RelatedContentResponse::from)
                        .toList())
                .build();
    }
}
