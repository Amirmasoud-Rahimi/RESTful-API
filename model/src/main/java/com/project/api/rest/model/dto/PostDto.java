package com.project.api.rest.model.dto;

import com.project.api.rest.model.entity.Post;
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
public class PostDto {
    private int id;
    private int userId;
    private String title;
    private String body;

    public static PostDto mapPostToPostDto(Post post) {
        return PostDto.builder()
                .id(post.getId())
                .userId(post.getUserId())
                .title(post.getTitle())
                .body(post.getBody())
                .build();
    }

    public static Post mapPostDtoToPost(PostDto postDto) {
        Post post = new Post();
        post.setId(postDto.getId());
        post.setBody(postDto.getBody());
        post.setTitle(postDto.getTitle());
        post.setUserId(postDto.getUserId());
        return post;
    }
}