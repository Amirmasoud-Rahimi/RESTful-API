package com.project.api.rest.service.api;

import com.project.api.rest.model.ToDo;

import java.io.IOException;
import java.util.List;

public interface ToDoService {
    List<ToDo> getToDoListFromUrl(String url) throws IOException;

    void saveToDoList() throws IOException;
}