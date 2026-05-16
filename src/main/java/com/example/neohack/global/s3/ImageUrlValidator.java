package com.example.neohack.global.s3;

import com.example.neohack.global.dto.response.exception.CustomException;
import com.example.neohack.global.dto.response.exception.ErrorCode;

import java.util.List;

/**
 * 이미지 URL이 ghostrip-bucket S3 경로로 시작하는지 검증한다.
 * 위반 시 400 Bad Request (INVALID_IMAGE_URL) 로 응답되도록 CustomException 을 던진다.
 */
public final class ImageUrlValidator {

    public static final String REQUIRED_PREFIX =
            "https://ghostrip-bucket.s3.ap-northeast-2.amazonaws.com/";

    private ImageUrlValidator() {}

    public static void validate(String imageUrl) {
        if (imageUrl == null || imageUrl.isBlank() || !imageUrl.startsWith(REQUIRED_PREFIX)) {
            throw new CustomException(ErrorCode.INVALID_IMAGE_URL);
        }
    }

    public static void validateAll(List<String> imageUrls) {
        if (imageUrls == null || imageUrls.isEmpty()) {
            throw new CustomException(ErrorCode.INVALID_IMAGE_URL);
        }
        for (String url : imageUrls) {
            validate(url);
        }
    }
}
