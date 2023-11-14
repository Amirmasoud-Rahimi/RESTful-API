package com.project.api.rest.service.repository;

import com.project.api.rest.model.entity.Comment;
import com.project.api.rest.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> getCommentByPostId(int postId);
}