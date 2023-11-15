package com.project.api.rest.server;

import com.project.api.rest.service.api.CommentService;
import com.project.api.rest.service.api.PostService;
import com.project.api.rest.service.api.ToDoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@SpringBootApplication(scanBasePackages = {"com.project.api.rest.model", "com.project.api.rest.server", "com.project.api.rest.service"})
@EntityScan(basePackages = {"com.project.api.rest.model", "com.project.api.rest.server", "com.project.api.rest.service"})
@EnableJpaRepositories(basePackages = {"com.project.api.rest.service.repository"})
public class ServerApplication {
    Logger logger = LoggerFactory.getLogger(ServerApplication.class);
    private final PostService postService;
    private final CommentService commentService;
    private final ToDoService toDoService;

    public ServerApplication(PostService postService, CommentService commentService, ToDoService toDoService) {
        this.postService = postService;
        this.commentService = commentService;
        this.toDoService = toDoService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup() throws IOException {
        logger.info("Hello World!, Rest Api Application just started up");
        logger.info("please wait.Date is fetching from url and saving in h2 db");
        postService.savePostList();
        commentService.saveCommentList();
        toDoService.saveToDoList();
        logger.info("save data completed");
    }
}