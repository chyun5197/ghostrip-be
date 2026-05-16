package com.example.neohack.domain.spot.controller;

import com.example.neohack.domain.spot.dto.request.ImageConvertRequest;
import com.example.neohack.domain.spot.dto.request.SpotMoodConvertRequest;
import com.example.neohack.domain.spot.dto.response.ImageConvertResponse;
import com.example.neohack.domain.spot.facade.SpotFacade;
import com.example.neohack.global.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/spot/convert/image")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Spot Convert API", description = "심령스팟 관련 API")
public class SpotImageConvertController {

    private final SpotFacade spotFacade;

    @PostMapping("/human-bg")
    @Operation(summary = "심령스팟 합성 이미지 생성(제대로 안됨)", description = "인물 사진 URL과 심령스팟 배경 URL을 AI로 합성합니다.")
    public ResponseEntity<ApiResponse<ImageConvertResponse>> convertImage(
            @RequestBody ImageConvertRequest request
    ) throws Exception {
        return ResponseEntity.ok(ApiResponse.success(spotFacade.convertImage(request)));
    }

    @PostMapping("/mood")
    @Operation(summary = "심령스팟 분위기 변환", description = "배경 이미지 URL을 받아 심령사진 분위기로 변환합니다.")
    public ResponseEntity<ApiResponse<ImageConvertResponse>> convertMood(
            @RequestBody SpotMoodConvertRequest request
    ) throws Exception {
        return ResponseEntity.ok(ApiResponse.success(spotFacade.convertMood(request)));
    }
}
