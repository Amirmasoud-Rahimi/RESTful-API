package com.project.api.rest.service.repository;

import com.project.api.rest.model.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer> {
}