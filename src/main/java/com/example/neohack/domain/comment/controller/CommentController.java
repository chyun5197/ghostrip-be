package com.example.neohack.domain.comment.controller;

import com.example.neohack.domain.comment.dto.CommentRequest;
import com.example.neohack.domain.comment.dto.CommentResponse;
import com.example.neohack.domain.comment.service.CommentService;
import com.example.neohack.global.dto.response.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Comment API", description = "심령스팟 상세 페이지 댓글 API")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{spotId}")
    @Operation(summary = "댓글 등록", description = "심령스팟에 댓글을 등록합니다.")
    public ResponseEntity<ApiResponse<CommentResponse>> registerComment(
            @PathVariable Long spotId,
            @RequestBody CommentRequest request
    ) {
        return ResponseEntity.ok(ApiResponse.success(commentService.registerComment(spotId, request)));
    }

    @GetMapping("/{spotId}")
    @Operation(summary = "댓글 목록 조회", description = "spotId에 해당하는 댓글 목록을 등록순으로 조회합니다.")
    public ResponseEntity<ApiResponse<List<CommentResponse>>> getCommentsBySpotId(
            @PathVariable Long spotId
    ) {
        return ResponseEntity.ok(ApiResponse.success(commentService.getCommentsBySpotId(spotId)));
    }
}
