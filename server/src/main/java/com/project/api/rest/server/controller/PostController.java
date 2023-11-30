package com.project.api.rest.server.controller;

import com.project.api.rest.model.dto.PostDto;
import com.project.api.rest.model.entity.Comment;
import com.project.api.rest.model.entity.Post;
import com.project.api.rest.service.aop.RestApiLogger;
import com.project.api.rest.service.api.CommentService;
import com.project.api.rest.service.api.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/postApi")
@Api(produces = "application/json", value = "Post Entity Controller")
public class PostController {
    private final PostService postService;
    private final CommentService commentService;

    public PostController(PostService postService, CommentService commentService) {
        this.postService = postService;
        this.commentService = commentService;
    }

    @ApiOperation(value = "getAllPostsByPagination", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetch data"),
            @ApiResponse(code = 500, message = "Error occurred in method process"),
    })
    @GetMapping
    public ResponseEntity<List<Post>> getAllPostsByPagination(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        List<Post> postList = postService.getPostListByPagination(pageNumber, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(postList);
    }

    @ApiOperation(value = "getPostById", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetch data"),
            @ApiResponse(code = 404, message = "Could not find entity with Submitted Id"),
            @ApiResponse(code = 500, message = "Error occurred in method process"),
    })
    @GetMapping("/posts/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable("postId") int postId) {
        Post post = postService.getPostById(postId);
        post.add(linkTo(methodOn(PostController.class).getPostById(postId)).withSelfRel());
        post.add(linkTo(methodOn(PostController.class).getAllPostsByPagination(0, 10)).withRel(IanaLinkRelations.COLLECTION));
        return ResponseEntity.status(HttpStatus.OK).body(post);
    }

    @ApiOperation(value = "getCommentListByPostId", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetch data"),
            @ApiResponse(code = 500, message = "Error occurred in method process"),
    })
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<Comment>> getCommentListByPostId(@PathVariable("postId") int postId) {
        List<Comment> commentList = commentService.getCommentListByPostId(postId);
        return ResponseEntity.status(HttpStatus.OK).body(commentList);
    }

    @ApiOperation(value = "getPostListByTitle", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetch data"),
            @ApiResponse(code = 500, message = "Error occurred in method process"),
    })
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPostListByTitle(@RequestParam("title") String title) {
        List<Post> postList = postService.getPostListByTitle(title);
        return ResponseEntity.status(HttpStatus.OK).body(postList);
    }

    @ApiOperation(value = "createPost", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Post Entity Created Successfully"),
            @ApiResponse(code = 500, message = "Error occurred in method process"),
    })
    @RestApiLogger
    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostDto postDto) {
        postService.createPost(postDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Post Entity Created Successfully. postId = " + postDto.getId());
    }

    @ApiOperation(value = "updatePostById", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Post Entity Updated Successfully"),
            @ApiResponse(code = 404, message = "Could not find Claim with Submitted Id"),
            @ApiResponse(code = 500, message = "Error occurred in method process"),
    })
    @PatchMapping("/posts/{postId}")
    public ResponseEntity<String> updatePostById(@PathVariable(name = "postId") int postId, @RequestBody PostDto postDto) {
        postService.updatePostByPostId(postId, postDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Post Entity Updated Successfully. postId = " + postId);
    }

    @ApiOperation(value = "deletePostById", response = void.class)
    @ApiResponses(value = {
            @ApiResponse(code = 204, message = "Post Entity Deleted Successfully"),
            @ApiResponse(code = 404, message = "Could not find Entity with Submitted Id"),
            @ApiResponse(code = 500, message = "Error occurred in method process"),
    })
    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deletePostById(@PathVariable(name = "postId") int postId) {
        postService.deletePostByPostId(postId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Post Entity Deleted Successfully. postId = " + postId);
    }
}