package com.project.api.rest.server;

import com.project.api.rest.service.api.PostService;
import com.project.api.rest.model.Post;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "api")
public class ApiController {

    private PostService postService;

    public ApiController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("posts")
    public List<Post> getPosts() throws IOException {
        return postService.getPostListFromUrl("https://jsonplaceholder.typicode.com/posts");
    }
}