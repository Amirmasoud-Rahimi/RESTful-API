package com.project.api.rest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "POST")
public class Post {
    @Id
    private int id;

    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "BODY")
    private String body;
}