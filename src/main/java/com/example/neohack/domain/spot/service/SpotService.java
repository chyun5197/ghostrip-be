package com.example.neohack.domain.spot.service;

import com.example.neohack.domain.spot.dto.SpotDetailResponse;
import com.example.neohack.domain.spot.entity.Spot;
import com.example.neohack.domain.spot.repository.SpotRepository;
import com.example.neohack.global.dto.response.exception.CustomException;
import com.example.neohack.global.dto.response.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SpotService {

    private final SpotRepository spotRepository;

    @Transactional
    public SpotDetailResponse getSpotDetail(String spotId) {
        Spot spot = spotRepository.findById(spotId)
                .orElseThrow(() -> new CustomException(ErrorCode.SPOT_NOT_FOUND));

        spot.increaseViewCount();

//        return SpotDetailResponse.from(spot);
        return null;
    }
}
