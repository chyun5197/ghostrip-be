package com.example.neohack.domain.comment.service;

import com.example.neohack.domain.comment.dto.CommentRequest;
import com.example.neohack.domain.comment.dto.CommentResponse;
import com.example.neohack.domain.comment.entity.Comment;
import com.example.neohack.domain.comment.repository.CommentRepository;
import com.example.neohack.domain.spot.entity.Spot;
import com.example.neohack.domain.spot.repository.SpotRepository;
import com.example.neohack.global.dto.response.exception.CustomException;
import com.example.neohack.global.dto.response.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final SpotRepository spotRepository;
    private final Random random = new Random();

    @Transactional
    public CommentResponse registerComment(Long spotId, CommentRequest request) {
        Spot spot = spotRepository.findById(spotId)
                .orElseThrow(() -> new CustomException(ErrorCode.SPOT_NOT_FOUND));

        Comment comment = Comment.builder()
                .content(request.getContent())
                .userName(generateUniqueUserName())
                .spot(spot)
                .build();

        if (request.getFearLevel() != null) {
            spot.updateFearLevel(request.getFearLevel());
        }

        return CommentResponse.from(commentRepository.save(comment));
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> getCommentsBySpotId(Long spotId) {
        return commentRepository.findBySpot_SpotIdOrderByCreatedAtAsc(spotId)
                .stream()
                .map(CommentResponse::from)
                .toList();
    }

    private String generateUniqueUserName() {
        String userName;
        do {
            userName = String.format("user%03d", random.nextInt(1000));
        } while (commentRepository.existsByUserName(userName));
        return userName;
    }
}
