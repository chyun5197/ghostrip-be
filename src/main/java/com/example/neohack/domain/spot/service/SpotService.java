package com.example.neohack.domain.spot.service;

import com.example.neohack.domain.spot.dto.request.SpotImageRequest;
import com.example.neohack.domain.spot.dto.response.SpotDetailResponse;
import com.example.neohack.domain.spot.dto.response.SpotImageResponse;
import com.example.neohack.domain.spot.entity.Spot;
import com.example.neohack.domain.spot.entity.SpotImage;
import com.example.neohack.domain.spot.repository.SpotImageRepository;
import com.example.neohack.domain.spot.repository.SpotRepository;
import com.example.neohack.global.dto.response.exception.CustomException;
import com.example.neohack.global.dto.response.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpotService {

    private final SpotRepository spotRepository;
    private final SpotImageRepository spotImageRepository;

    @Transactional(readOnly = true)
    public List<SpotDetailResponse> getBestSpots() {
        return spotRepository.findAllByOrderByViewCountDesc(PageRequest.of(0, 10))
                .stream()
                .map(SpotDetailResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public Spot getSpotDetail(Long spotId) {
        return spotRepository.findById(spotId)
                .orElseThrow(() -> new CustomException(ErrorCode.SPOT_NOT_FOUND));
    }

    @Transactional
    public List<SpotImageResponse> addSpotImages(Long spotId, SpotImageRequest request) {
        Spot spot = spotRepository.findById(spotId)
                .orElseThrow(() -> new CustomException(ErrorCode.SPOT_NOT_FOUND));

        List<SpotImage> images = request.getImageUrlList().stream()
                .map(url -> SpotImage.builder()
                        .imageUrl(url)
                        .spot(spot)
                        .build())
                .toList();

        return spotImageRepository.saveAll(images).stream()
                .map(SpotImageResponse::from)
                .toList();
    }
}
