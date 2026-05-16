package com.example.neohack.global.dto.response.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 400 Bad Request
    INVALID_INPUT(HttpStatus.BAD_REQUEST, "E001", "잘못된 입력입니다.");
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}
