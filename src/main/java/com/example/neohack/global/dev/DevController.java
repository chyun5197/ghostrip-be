package com.example.neohack.global.dev;

import com.example.neohack.global.dto.response.ApiResponse;
import com.example.neohack.global.dto.response.exception.ErrorDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/dev")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Dev API", description = "개발 편의 기능을 위한 API")
public class DevController {

    @GetMapping("/success")
    @Operation(summary = "성공 응답 예시", description = "data가 있는 성공 응답 형태입니다.")
    public ResponseEntity<ApiResponse<Map<String, Object>>> successExample() {
        Map<String, Object> data = Map.of(
                "id", 1,
                "name", "홍길동",
                "email", "hong@example.com"
        );
        return ResponseEntity.ok(ApiResponse.success(data));
    }

    @GetMapping("/success/no-data")
    @Operation(summary = "성공 응답 예시 (반환 data 없음)", description = "data가 없는 성공 응답 형태입니다.")
    public ResponseEntity<ApiResponse<Void>> successNoDataExample() {
        return ResponseEntity.ok(ApiResponse.success());
    }

    @GetMapping("/error/not-found")
    @Operation(summary = "실패 응답 예시 (404)", description = "리소스를 찾을 수 없는 실패 응답 형태입니다.")
    public ResponseEntity<ApiResponse<Void>> errorNotFoundExample() {
        return ResponseEntity.status(404).body(ApiResponse.error(
                ErrorDetail.builder()
                        .code("SPOT-404")
                        .message("요청한 장소를 찾을 수 없습니다.")
                        .build()
        ));
    }

    @GetMapping("/error/forbidden")
    @Operation(summary = "실패 응답 예시 (403)", description = "권한이 없는 실패 응답 형태입니다.")
    public ResponseEntity<ApiResponse<Void>> errorForbiddenExample() {
        return ResponseEntity.status(403).body(ApiResponse.error(
                ErrorDetail.builder()
                        .code("IMAGE-403")
                        .message("해당 이미지에 대한 권한이 없습니다.")
                        .build()
        ));
    }

    @GetMapping("/error/conflict")
    @Operation(summary = "실패 응답 예시 (409)", description = "중복 데이터 실패 응답 형태입니다.")
    public ResponseEntity<ApiResponse<Void>> errorConflictExample() {
        return ResponseEntity.status(409).body(ApiResponse.error(
                ErrorDetail.builder()
                        .code("TITLE-409")
                        .message("이미 존재하는 제목입니다.")
                        .build()
        ));
    }
}
