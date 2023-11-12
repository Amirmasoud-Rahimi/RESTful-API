package com.project.api.rest.service.business;

import com.project.api.rest.service.api.PostService;
import com.project.api.rest.model.Post;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.project.api.rest.service.repository.PostRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getPostListFromUrl(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        String json = IOUtils.toString(url, StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<List<Post>>() {
        });
    }

    public void savePostList() throws IOException {
        List<Post> postList = getPostListFromUrl("https://jsonplaceholder.typicode.com/posts");
        postRepository.saveAll(postList);
    }
}