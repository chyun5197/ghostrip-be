package com.example.neohack.domain.spot.repository;

import com.example.neohack.domain.spot.entity.RelatedContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelatedContentRepository extends JpaRepository<RelatedContent, Long> {
    List<RelatedContent> findBySpot_SpotId(Long spotId);
}
