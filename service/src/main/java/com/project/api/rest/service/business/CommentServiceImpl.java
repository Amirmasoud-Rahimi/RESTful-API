package com.project.api.rest.service.business;

import com.project.api.rest.model.Comment;
import com.project.api.rest.service.api.CommentService;
import com.project.api.rest.service.repository.CommentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getCommentListFromUrl(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        String json = IOUtils.toString(url, StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<List<Comment>>() {
        });
    }

    public void saveCommentList() throws IOException {
        List<Comment> postList = getCommentListFromUrl("https://jsonplaceholder.typicode.com/comments");
        commentRepository.saveAll(postList);
    }
}