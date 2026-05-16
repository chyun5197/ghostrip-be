package com.example.neohack.domain.comment.repository;

import com.example.neohack.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {
    List<Comment> findBySpot_SpotIdOrderByCreatedAtAsc(String spotId);
    boolean existsByUserName(String userName);
}
