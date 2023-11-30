package com.project.api.rest.model.dto;

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
public class ToDoDto {
    private int id;
    private int userId;
    private String title;
    private boolean completed;
}