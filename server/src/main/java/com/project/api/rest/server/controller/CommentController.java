package com.project.api.rest.server.controller;

import com.project.api.rest.model.dto.CommentDto;
import com.project.api.rest.model.entity.Comment;
import com.project.api.rest.service.api.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

@RestController
@RequestMapping("/commentApi")
@Api(produces = "application/json", value = "Comment Entity Controller")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @ApiOperation(value = "getAllCommentsByPagination", response = ResponseEntity.class)
    @ApiResponses(value = { //Swagger Document
            @ApiResponse(code = 200, message = "Successfully fetch data"),
            @ApiResponse(code = 500, message = "Error occurred in method process"),
    })
    @GetMapping
    public ResponseEntity<List<Comment>> getAllCommentsByPagination(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        List<Comment> commentList = commentService.getCommentListByPagination(pageNumber, pageSize);
        return ResponseEntity.status(HttpStatus.OK).body(commentList);
    }

    @ApiOperation(value = "getCommentListByPostId", response = ResponseEntity.class)
    @ApiResponses(value = { //Swagger Document
            @ApiResponse(code = 200, message = "Successfully fetch data"),
            @ApiResponse(code = 500, message = "Error occurred in method process"),
    })
    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getCommentListByPostId(@RequestParam(value = "postId") int postId) {
        List<Comment> commentList = commentService.getCommentListByPostId(postId);
        return ResponseEntity.status(HttpStatus.OK).body(commentList);
    }

    @ApiOperation(value = "createComment", response = void.class)
    @ApiResponses(value = { //Swagger Document
            @ApiResponse(code = 200, message = "Successfully create entity"),
            @ApiResponse(code = 500, message = "Error occurred in method process"),
    })
    @PostMapping
    public void createComment(@RequestBody CommentDto commentDto) {
        commentService.createComment(commentDto);
    }

    @ApiOperation(value = "updateCommentById", response = void.class)
    @ApiResponses(value = { //Swagger Document
            @ApiResponse(code = 200, message = "Successfully update entity"),
            @ApiResponse(code = 404, message = "Could not find entity with Submitted Id"),
            @ApiResponse(code = 500, message = "Error occurred in method process"),
    })
    @PatchMapping("/comments/{commentId}")
    public void updateCommentById(@PathVariable(name = "commentId") int commentId, @RequestBody CommentDto commentDto) {
        commentService.updateCommentByCommentId(commentId, commentDto);
    }

    @ApiOperation(value = "deleteCommentById", response = void.class)
    @ApiResponses(value = { //Swagger Document
            @ApiResponse(code = 200, message = "Successfully delete entity"),
            @ApiResponse(code = 404, message = "Could not find entity with Submitted Id"),
            @ApiResponse(code = 500, message = "Error occurred in method process"),
    })
    @DeleteMapping("/comments/{commentId}")
    public void deleteCommentById(@PathVariable(name = "commentId") int commentId) {
        commentService.deleteCommentByCommentId(commentId);
    }
}