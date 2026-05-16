package com.example.neohack.domain.spot.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ImageConvertRequest {
    private String personImageUrl;
    private String spotImageUrl;
}
