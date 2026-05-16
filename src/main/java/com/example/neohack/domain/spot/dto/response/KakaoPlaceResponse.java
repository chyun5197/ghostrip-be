package com.example.neohack.domain.spot.dto.response;

import com.example.neohack.domain.spot.entity.KakaoPlace;
import lombok.Getter;

@Getter
public class KakaoPlaceResponse {
    private Long kakaoPlaceId;
    private String address;
    private String roadAddress;
    private Double latitude;
    private Double longitude;
    private String phone;
    private String kakaoPlaceUrl;
    private String categoryName;

    public static KakaoPlaceResponse from(KakaoPlace kakaoPlace) {
        KakaoPlaceResponse response = new KakaoPlaceResponse();
        response.kakaoPlaceId = kakaoPlace.getKakaoPlaceId();
        response.address = kakaoPlace.getAddress();
        response.roadAddress = kakaoPlace.getRoadAddress();
        response.latitude = kakaoPlace.getLatitude();
        response.longitude = kakaoPlace.getLongitude();
        response.phone = kakaoPlace.getPhone();
        response.kakaoPlaceUrl = kakaoPlace.getKakaoPlaceUrl();
        response.categoryName = kakaoPlace.getCategoryName();
        return response;
    }
}
