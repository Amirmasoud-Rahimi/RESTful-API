package com.project.api.rest.model.dto;

import com.project.api.rest.model.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private int id;
    private String name;
    private String email;
    private String body;
    private int postId;

    public static Comment mapCommentDtoToComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        comment.setPostId(commentDto.getPostId());
        return comment;
    }
}