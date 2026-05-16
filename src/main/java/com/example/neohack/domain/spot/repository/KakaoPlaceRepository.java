package com.example.neohack.domain.spot.repository;

import com.example.neohack.domain.spot.entity.KakaoPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KakaoPlaceRepository extends JpaRepository<KakaoPlace, Long> {
    Optional<KakaoPlace> findBySpot_SpotId(Long spotId);
}
