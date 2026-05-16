package com.example.neohack.domain.spot.facade;

import com.example.neohack.domain.spot.dto.request.SpotImageRequest;
import com.example.neohack.domain.spot.dto.response.SpotDetailResponse;
import com.example.neohack.domain.spot.dto.response.SpotImageResponse;
import com.example.neohack.domain.spot.entity.Spot;
import com.example.neohack.domain.spot.service.SpotService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SpotFacade {
    private final SpotService spotService;

    public SpotDetailResponse getSpotDetail(String spotId) {
        Spot spot = spotService.getSpotDetail(spotId);
        return SpotDetailResponse.from(spot);
    }

    public List<SpotImageResponse> addSpotImages(String spotId, SpotImageRequest request) {
        return spotService.addSpotImages(spotId, request);
    }
}
