package com.example.neohack.global.health;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/health")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "HealthCheck API", description = "서버 상태 확인을 위한 API")
public class HealthCheck {

    private final DataSource dataSource;

    @GetMapping
    @Operation(summary = "서버 및 DB 상태 확인")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> status = new LinkedHashMap<>();
        status.put("server", "Active");
        status.put("db", checkDatabase());

        boolean allUp = status.values().stream().allMatch("Active"::equals);
        return allUp ? ResponseEntity.ok(status) : ResponseEntity.status(503).body(status);
    }

    private String checkDatabase() {
        try (Connection connection = dataSource.getConnection()) {
            return connection.isValid(3) ? "UP" : "DOWN";
        } catch (Exception e) {
            log.error("Database health check failed", e);
            return "DOWN";
        }
    }
}