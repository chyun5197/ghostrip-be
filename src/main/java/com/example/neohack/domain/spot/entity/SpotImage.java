package com.example.neohack.domain.spot.entity;

import com.example.neohack.global.entity.BaseTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "spot_image")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SpotImage extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "spot_image_id")
    private String spotImageId;

    @Column(name = "image_url")
    private String imageUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id")
    private Spot spot;
}
