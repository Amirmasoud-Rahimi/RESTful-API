package com.project.api.rest.service.api;

import com.project.api.rest.model.Comment;

import java.io.IOException;
import java.util.List;

public interface CommentService {
    List<Comment> getCommentListFromUrl(String url) throws IOException;
    void saveCommentList() throws IOException;
}