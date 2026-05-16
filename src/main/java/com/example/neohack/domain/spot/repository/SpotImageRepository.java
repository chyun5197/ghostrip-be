package com.example.neohack.domain.spot.repository;

import com.example.neohack.domain.spot.entity.SpotImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpotImageRepository extends JpaRepository<SpotImage, String> {

    List<SpotImage> findBySpot_SpotId(String spotId);
}
