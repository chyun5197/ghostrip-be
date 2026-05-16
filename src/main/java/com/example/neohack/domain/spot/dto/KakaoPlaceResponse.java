package com.example.neohack.domain.spot.dto;

import com.example.neohack.domain.spot.entity.KakaoPlace;
import lombok.Builder;
import lombok.Getter;

@Getter
public class KakaoPlaceResponse {
    private String address;
    private String roadAddress;
    private Double latitude;
    private Double longitude;
    private String phone;
    private String kakaoPlaceUrl;
    private String categoryName;

    public static KakaoPlaceResponse from(KakaoPlace kakaoPlace) {
        KakaoPlaceResponse response = new KakaoPlaceResponse();
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
