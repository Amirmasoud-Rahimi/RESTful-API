package com.project.api.rest.model.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "TO_DO")
public class ToDo {
    @Id
    private int id;

    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "COMPLETED")
    private boolean completed;
}