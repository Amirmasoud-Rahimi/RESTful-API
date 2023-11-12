package com.project.api.rest.service.business;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.api.rest.model.ToDo;
import com.project.api.rest.service.api.ToDoService;
import com.project.api.rest.service.repository.ToDoRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class ToDoServiceImpl implements ToDoService {
    private final ToDoRepository toDoRepository;

    public ToDoServiceImpl(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    public List<ToDo> getToDoListFromUrl(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        String json = IOUtils.toString(url, StandardCharsets.UTF_8);
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, new TypeReference<List<ToDo>>() {
        });
    }

    public void saveToDoList() throws IOException {
        List<ToDo> toDoList = getToDoListFromUrl("https://jsonplaceholder.typicode.com/todos");//TODO read from config
        toDoRepository.saveAll(toDoList);
    }
}