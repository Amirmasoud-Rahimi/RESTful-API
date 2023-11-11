package com.project.api.rest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Comment {

    @Id
    private int id;
    @Column(name = "POST_ID")
    private int postId;
    private String name;
    private String email;
    private String body;
}