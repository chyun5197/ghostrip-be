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
    private List<String> imageUrlList;

    private KakaoPlaceResponse kakaoPlace;
    private List<RelatedContentResponse> relatedContentList;

    @Getter
    @Builder
    public static class KakaoPlaceResponse {
        private String address;
        private String roadAddressName;
        private Double latitude;
        private Double longitude;
        private String phone;
        private String kakaoPlaceUrl;
        private String categoryName;

        public static KakaoPlaceResponse from(KakaoPlace kakaoPlace) {
            if (kakaoPlace == null) return null;
            return KakaoPlaceResponse.builder()
                    .address(kakaoPlace.getAddress())
                    .roadAddressName(kakaoPlace.getRoadAddressName())
                    .latitude(kakaoPlace.getLatitude())
                    .longitude(kakaoPlace.getLongitude())
                    .phone(kakaoPlace.getPhone())
                    .kakaoPlaceUrl(kakaoPlace.getKakaoPlaceUrl())
                    .categoryName(kakaoPlace.getCategoryName())
                    .build();
        }
    }

    @Getter
    @Builder
    public static class RelatedContentResponse {
        private String title;
        private String thumbUrl;

        public static RelatedContentResponse from(RelatedContent content) {
            return RelatedContentResponse.builder()
                    .title(content.getTitle())
                    .thumbUrl(content.getThumbUrl())
                    .build();
        }
    }

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
