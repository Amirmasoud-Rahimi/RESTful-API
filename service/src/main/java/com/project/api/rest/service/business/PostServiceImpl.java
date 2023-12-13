package com.project.api.rest.service.business;

import com.project.api.rest.model.dto.PostDto;
import com.project.api.rest.model.exception.EntityNotFoundException;
import com.project.api.rest.service.api.PostService;
import com.project.api.rest.model.entity.Post;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import com.project.api.rest.service.repository.PostRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    @Value("${postUrl}")
    private String postUrl;
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getPostListByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Post> page = postRepository.findAll(pageable);
        return page.getContent();
    }

    @Override
    @Cacheable(value = "postCache")
    public Post getPostById(int postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        return postOptional.orElseThrow(() -> new EntityNotFoundException("Post Not Found!"));
    }

    @Override
    public List<Post> getPostListByTitle(String title) {
        return postRepository.getPostByTitleContainingIgnoreCase(title);
    }

    @Override
    public void createPost(PostDto postDto) {
        Post post = PostDto.mapPostDtoToPost(postDto);
        postRepository.save(post);
    }

    @Override
    public void updatePostByPostId(int postId, PostDto postDto) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (postOptional.isPresent()) {
            Post post = PostDto.mapPostDtoToPost(postDto);
            postRepository.save(post);
        }
    }

    @Override
    public void deletePostByPostId(int postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public List<Post> getPostListFromUrl(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        String json = IOUtils.toString(url, StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<List<Post>>() {
        });
    }

    @Override
    public void savePostList() throws IOException {
        List<Post> postList = getPostListFromUrl(postUrl);
        postRepository.saveAll(postList);
    }
}