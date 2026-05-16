package com.example.neohack.domain.spot.repository;

import com.example.neohack.domain.spot.entity.Spot;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpotRepository extends JpaRepository<Spot, Long> {
    List<Spot> findAllByOrderByViewCountDesc(Pageable pageable);
}
