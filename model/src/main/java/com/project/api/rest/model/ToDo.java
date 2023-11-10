package com.project.api.rest.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToDo {
    private int userId;
    private int id;
    private String title;
    private boolean completed;
}