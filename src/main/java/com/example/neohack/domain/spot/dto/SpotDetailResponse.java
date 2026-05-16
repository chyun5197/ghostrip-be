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

    private String spotId;
    private String name;
    private String description;
    private Double fearLevel;
    private String visitWarning;
    private Long viewCount;
    private List<String> imageUrls;

//    private KakaoPlace kakaoPlace;
//    private List<RelatedContentInfo> relatedContents;

//    public static SpotDetailResponse from(Spot spot) {
//        return SpotDetailResponse.builder()
//                .spotId(spot.getSpotId())
//                .name(spot.getName())
//                .description(spot.getDescription())
//                .fearLevel(spot.getFearLevel())
//                .visitWarning(spot.getVisitWarning())
//                .viewCount(spot.getViewCount())
////                .kakaoPlace(KakaoPlaceInfo.from(spot.getKakaoPlace()))
//                .imageUrls(spot.getSpotImages().stream()
//                        .map(SpotImage::getImageUrl)
//                        .toList())
////                .relatedContents(spot.getRelatedContents().stream()
////                        .map(RelatedContentInfo::from)
//                        .toList())
//                .build();
//    }
}
