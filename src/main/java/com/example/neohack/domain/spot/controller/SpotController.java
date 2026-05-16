package com.example.neohack.domain.spot.controller;

import com.example.neohack.domain.spot.dto.SpotDetailResponse;
import com.example.neohack.domain.spot.facade.SpotFacade;
import com.example.neohack.domain.spot.service.SpotService;
import com.example.neohack.global.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/spot")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Spot API", description = "심령스팟 관련 API")
public class SpotController {

    private final SpotFacade spotFacade;

    @GetMapping("/{spotId}")
    @Operation(summary = "심령스팟 상세 정보 조회", description = "spotId로 심령스팟 상세 정보를 조회합니다. 조회 시 viewCount가 1 증가합니다.")
    public ResponseEntity<ApiResponse<SpotDetailResponse>> getSpotDetail(
            @PathVariable String spotId
    ) {
        return ResponseEntity.ok(ApiResponse.success(spotFacade.getSpotDetail(spotId)));
    }
}
