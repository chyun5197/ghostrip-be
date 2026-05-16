package com.example.neohack.domain.spot.controller;

import com.example.neohack.domain.spot.dto.request.SpotImageRequest;
import com.example.neohack.domain.spot.dto.response.SpotDetailResponse;
import com.example.neohack.domain.spot.dto.response.SpotImageResponse;
import com.example.neohack.domain.spot.facade.SpotFacade;
import com.example.neohack.global.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/spot")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Spot API", description = "심령스팟 관련 API")
public class SpotController {

    private final SpotFacade spotFacade;

    @GetMapping("/best")
    @Operation(summary = "베스트 심령스팟 조회", description = "viewCount 기준 상위 10개 심령스팟을 반환합니다.")
    public ResponseEntity<ApiResponse<List<SpotDetailResponse>>> getBestSpots() {
        return ResponseEntity.ok(ApiResponse.success(spotFacade.getBestSpots()));
    }

    @GetMapping("/{spotId}")
    @Operation(summary = "심령스팟 상세 정보 조회", description = "spotId로 심령스팟 상세 정보를 조회합니다. 조회 시 viewCount가 1 증가합니다.")
    public ResponseEntity<ApiResponse<SpotDetailResponse>> getSpotDetail(
            @PathVariable Long spotId
    ) {
        return ResponseEntity.ok(ApiResponse.success(spotFacade.getSpotDetail(spotId)));
    }

    @PostMapping("/{spotId}/image")
    @Operation(summary = "심령스팟 이미지 등록", description = "S3 업로드 완료 후 imageUrl 리스트를 전달하면 spotImage에 저장합니다.")
    public ResponseEntity<ApiResponse<List<SpotImageResponse>>> addSpotImages(
            @PathVariable Long spotId,
            @RequestBody SpotImageRequest request
    ) {
        return ResponseEntity.ok(ApiResponse.success(spotFacade.addSpotImages(spotId, request)));
    }
}
