package com.example.neohack.domain.comment.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequest {
    private String content;

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "5.0")
    private Double fearLevel;
}
