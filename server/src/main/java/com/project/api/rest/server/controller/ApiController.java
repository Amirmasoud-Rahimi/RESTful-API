package com.project.api.rest.server.controller;

import com.project.api.rest.model.Comment;
import com.project.api.rest.model.ToDo;
import com.project.api.rest.service.api.CommentService;
import com.project.api.rest.service.api.PostService;
import com.project.api.rest.model.Post;
import com.project.api.rest.service.api.ToDoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "api")
public class ApiController {
    private final PostService postService;
    private final CommentService commentService;
    private final ToDoService toDoService;

    public ApiController(PostService postService, CommentService commentService, ToDoService toDoService) {
        this.postService = postService;
        this.commentService = commentService;
        this.toDoService = toDoService;
    }

    @GetMapping("posts")
    public List<Post> getPosts() throws IOException {
        return postService.getPostListFromUrl("https://jsonplaceholder.typicode.com/posts");
    }

    @PostMapping("savePosts")
    public void savePosts() throws IOException {
        postService.savePostList();
    }

    @GetMapping("comments")
    public List<Comment> getComments() throws IOException {
        return commentService.getCommentListFromUrl("https://jsonplaceholder.typicode.com/comments");
    }

    @PostMapping("saveComments")
    public void saveComments() throws IOException {
        commentService.saveCommentList();
    }

    @GetMapping("todos")
    public List<ToDo> getTodos() throws IOException {
        return toDoService.getToDoListFromUrl("https://jsonplaceholder.typicode.com/todos");
    }

    @PostMapping("saveTodos")
    public void saveTodos() throws IOException {
        toDoService.saveToDoList();
    }
}