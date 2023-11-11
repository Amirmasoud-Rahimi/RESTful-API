package com.project.api.rest.service.api;

import com.project.api.rest.model.Post;

import java.io.IOException;
import java.util.List;

public interface PostService {
    List<Post> getPostListFromUrl(String url) throws IOException;
}