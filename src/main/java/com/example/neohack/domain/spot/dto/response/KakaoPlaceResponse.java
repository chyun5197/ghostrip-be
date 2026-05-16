package com.example.neohack.domain.spot.dto.response;

import com.example.neohack.domain.spot.entity.KakaoPlace;
import lombok.Getter;

@Getter
public class KakaoPlaceResponse {
    private Long kakaoPlaceId;
    private String address;
    private String placeName;
    private String kakaoPlaceUrl;

    public static KakaoPlaceResponse from(KakaoPlace kakaoPlace) {
        KakaoPlaceResponse response = new KakaoPlaceResponse();
        response.kakaoPlaceId = kakaoPlace.getKakaoPlaceId();
        response.address = kakaoPlace.getAddress();
        response.placeName = kakaoPlace.getPlaceName();
        response.kakaoPlaceUrl = kakaoPlace.getKakaoPlaceUrl();
        return response;
    }
}
