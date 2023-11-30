package com.project.api.rest.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "COMMENT")
public class Comment extends RepresentationModel<Comment> {
    @Id
    private int id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "BODY")
    private String body;

    @Column(name = "POST_ID")
    private int postId;
}