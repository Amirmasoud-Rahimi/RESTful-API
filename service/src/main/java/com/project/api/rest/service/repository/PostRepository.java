package com.project.api.rest.service.repository;

import com.project.api.rest.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {
    List<Post> getPostByTitleContainingIgnoreCase(String title);
}