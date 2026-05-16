package com.example.neohack.domain.spot.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SpotImageRequest {
    private List<String> imageUrlList;
}
