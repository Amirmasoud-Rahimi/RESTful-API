package com.project.api.rest.model.dto;

import com.project.api.rest.model.entity.ToDo;
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

    public static ToDoDto mapToDoToToDoDto(ToDo toDo) {
        return ToDoDto.builder()
                .id(toDo.getId())
                .userId(toDo.getUserId())
                .title(toDo.getTitle())
                .completed(toDo.isCompleted())
                .build();
    }
}