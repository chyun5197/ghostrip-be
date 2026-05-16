package com.example.neohack.domain.spot.entity;

import com.example.neohack.global.entity.BaseTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "kakao_place")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class KakaoPlace extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "kakao_place_id")
    private Long kakaoPlaceId;

    @Column(name = "kakao_place_url")
    private String kakaoPlaceUrl;

    @Column(name = "address")
    private String address;

    @Column(name = "place_name")
    private String placeName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id")
    private Spot spot;
}
