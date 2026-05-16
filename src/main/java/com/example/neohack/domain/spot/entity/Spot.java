package com.example.neohack.domain.spot.entity;

import com.example.neohack.global.entity.BaseTime;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "spot")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Spot extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spot_id")
    private Long spotId;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "fear_level")
    private Double fearLevel;

    @Column(name = "visit_warning")
    private String visitWarning;

    @Column(name = "view_count")
    @Builder.Default
    private Long viewCount = 0L;

    @OneToOne(mappedBy = "spot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private KakaoPlace kakaoPlace;

    @OneToMany(mappedBy = "spot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<SpotImage> spotImages = new ArrayList<>();

    @OneToMany(mappedBy = "spot", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<RelatedContent> relatedContents = new ArrayList<>();

    public void increaseViewCount() {
        this.viewCount++;
    }
}
