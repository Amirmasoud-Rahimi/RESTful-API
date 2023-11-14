package com.project.api.rest.service.api;

import com.project.api.rest.model.dto.CommentDto;
import com.project.api.rest.model.entity.Comment;

import java.io.IOException;
import java.util.List;

public interface CommentService {
    List<Comment> getCommentListByPagination(int pageNumber, int pageSize);

    List<Comment> getCommentListByPostId(int postId);

    void createComment(CommentDto commentDto);

    void updateCommentByCommentId(int commentId, CommentDto commentDto);

    void deleteCommentByCommentId(int commentId);

    List<Comment> getCommentListFromUrl(String url) throws IOException;

    void saveCommentList() throws IOException;
}