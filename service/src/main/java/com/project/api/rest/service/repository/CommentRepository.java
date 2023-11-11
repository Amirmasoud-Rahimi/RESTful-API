package com.project.api.rest.service.repository;

import com.project.api.rest.model.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
}