package com.project.api.rest.service.repository;

import com.project.api.rest.model.entity.ToDo;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ToDoRepository extends CrudRepository<ToDo, Integer> {
    List<ToDo> getByUserIdAndCompleted(int userId,boolean completed);
}