package com.example.neohack.domain.spot.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/spot")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Spot API", description = "관광지 관련 API")
public class SpotController {
}
