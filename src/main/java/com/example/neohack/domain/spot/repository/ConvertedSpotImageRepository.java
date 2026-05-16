package com.example.neohack.domain.spot.repository;

import com.example.neohack.domain.spot.entity.ConvertedSpotImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConvertedSpotImageRepository extends JpaRepository<ConvertedSpotImage, Long> {
    List<ConvertedSpotImage> findBySpot_SpotId(Long spotId);
    List<ConvertedSpotImage> findByMember_MemberId(Long memberId);
}
