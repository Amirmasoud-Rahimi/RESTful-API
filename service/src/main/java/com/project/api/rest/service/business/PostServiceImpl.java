package com.project.api.rest.service.business;

import com.project.api.rest.service.api.PostService;
import com.project.api.rest.model.Post;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {
    public List<Post> getPostListFromUrl(String url) throws IOException {
        URL url1 = new URL("https://jsonplaceholder.typicode.com/posts");
        String json = IOUtils.toString(url1, StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<List<Post>>() {
        });
    }
}