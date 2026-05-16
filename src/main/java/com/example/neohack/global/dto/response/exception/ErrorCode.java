package com.example.neohack.global.dto.response.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 400 Bad Request
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "E001", "잘못된 입력입니다."),
    INVALID_IMAGE_URL(HttpStatus.BAD_REQUEST, "IMAGE-400",
            "이미지 URL은 ghostrip-bucket S3 경로(https://ghostrip-bucket.s3.ap-northeast-2.amazonaws.com/...) 여야 합니다."),

    // Spot
    SPOT_NOT_FOUND(HttpStatus.NOT_FOUND, "SPOT-404", "요청한 심령스팟을 찾을 수 없습니다.");
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
