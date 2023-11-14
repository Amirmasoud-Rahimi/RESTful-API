package com.project.api.rest.service.api;

import com.project.api.rest.model.dto.PostDto;
import com.project.api.rest.model.entity.Post;

import java.io.IOException;
import java.util.List;

public interface PostService {
    List<Post> getPostListByPagination(int pageNumber, int pageSize);

    Post getPostById(int postId);

    List<Post> getPostListByTitle(String title);

    void createPost(PostDto postDto);

    void updatePostByPostId(int postId, PostDto postDto);

    void deletePostByPostId(int postId);

    List<Post> getPostListFromUrl(String url) throws IOException;

    void savePostList() throws IOException;
}