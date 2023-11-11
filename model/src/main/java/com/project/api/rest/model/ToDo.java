package com.project.api.rest.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class ToDo {
    @Id
    private int id;
    @Column(name = "USER_ID")
    private int userId;
    private String title;
    private boolean completed;
}