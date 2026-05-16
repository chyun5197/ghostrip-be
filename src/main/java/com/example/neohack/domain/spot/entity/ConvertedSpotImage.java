package com.example.neohack.domain.spot.entity;

import com.example.neohack.domain.member.Member;
import com.example.neohack.global.entity.BaseTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "converted_spot_image")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class ConvertedSpotImage extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "converted_spot_image_id")
    private Long convertedSpotImageId;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id")
    private Spot spot;
}
