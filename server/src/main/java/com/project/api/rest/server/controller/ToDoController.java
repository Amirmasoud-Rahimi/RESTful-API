package com.project.api.rest.server.controller;

import com.project.api.rest.model.entity.ToDo;
import com.project.api.rest.service.api.ToDoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/todoApi")
@Api(produces = "application/json", value = "ToDo Entity Controller")
public class ToDoController {
    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @ApiOperation(value = "getAllToDos", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetch data"),
            @ApiResponse(code = 500, message = "Error occurred in method process"),
    })
    @GetMapping
    @CrossOrigin //Angular CORS Error Fixing
    public ResponseEntity<CollectionModel<ToDo>> getAllToDos() {
        List<ToDo> allToDos = toDoService.getAllToDos();
        CollectionModel<ToDo> collectionModel = CollectionModel.of(allToDos);
        collectionModel.add(linkTo(methodOn(ToDoController.class).getAllToDos()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(collectionModel);
    }

    @ApiOperation(value = "getToDoListByUserIdAndCompleted", response = ResponseEntity.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully fetch data"),
            @ApiResponse(code = 500, message = "Error occurred in method process"),
    })
    @GetMapping("/todos")
    public ResponseEntity<List<ToDo>> getToDoListByUserIdAndCompleted(
            @RequestParam(value = "userId") int userId, @RequestParam(value = "completed") boolean completed) {
        List<ToDo> toDoList = toDoService.getToDoListByUserIdAndCompleted(userId, completed);
        return ResponseEntity.status(HttpStatus.OK).body(toDoList);
    }
}