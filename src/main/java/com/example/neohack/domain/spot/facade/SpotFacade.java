package com.example.neohack.domain.spot.facade;

import com.example.neohack.domain.spot.dto.SpotDetailResponse;
import com.example.neohack.domain.spot.entity.Spot;
import com.example.neohack.domain.spot.service.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SpotFacade {
    private final SpotService spotService;

    public SpotDetailResponse getSpotDetail(String spotId) {

//        Spot spot = spotService.getSpotDetail(spotId);
        return null;
    }
}
