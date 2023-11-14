package com.project.api.rest.service.business;

import com.project.api.rest.model.dto.CommentDto;
import com.project.api.rest.model.entity.Comment;
import com.project.api.rest.service.api.CommentService;
import com.project.api.rest.service.repository.CommentRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Value("${commentUrl}")
    private String commentUrl;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getCommentListByPagination(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Comment> page = commentRepository.findAll(pageable);
        return page.getContent();
    }

    public List<Comment> getCommentListByPostId(int postId) {
        return commentRepository.getCommentByPostId(postId);
    }

    public void createComment(CommentDto commentDto) {
        Comment comment = CommentDto.mapCommentDtoToComment(commentDto);
        commentRepository.save(comment);
    }

    public void updateCommentByCommentId(int commentId, CommentDto commentDto) {
        Optional<Comment> commentOptional = commentRepository.findById(commentId);
        if (commentOptional.isPresent()) {
            Comment comment = CommentDto.mapCommentDtoToComment(commentDto);
            commentRepository.save(comment);
        }
    }

    public void deleteCommentByCommentId(int commentId) {
        commentRepository.deleteById(commentId);
    }

    public List<Comment> getCommentListFromUrl(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        String json = IOUtils.toString(url, StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<List<Comment>>() {
        });
    }

    public void saveCommentList() throws IOException {
        List<Comment> commentList = getCommentListFromUrl(commentUrl);
        commentRepository.saveAll(commentList);
    }
}