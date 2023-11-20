package com.project.api.rest.server.configuration;

import com.project.api.rest.service.api.CommentService;
import com.project.api.rest.service.api.PostService;
import com.project.api.rest.service.api.ToDoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

@Component
public class AppStartupListener {
    Logger logger = LoggerFactory.getLogger(AppStartupListener.class);
    private final PostService postService;
    private final CommentService commentService;
    private final ToDoService toDoService;

    public AppStartupListener(PostService postService, CommentService commentService, ToDoService toDoService) {
        this.postService = postService;
        this.commentService = commentService;
        this.toDoService = toDoService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void startupListener() throws IOException {
        fetchAndSaveData();
        launchBrowser();
    }

    private void fetchAndSaveData() throws IOException {
        logger.info("please wait.Data is fetching from url and saving in h2 db");
        postService.savePostList();
        commentService.saveCommentList();
        toDoService.saveToDoList();
        logger.info("save data completed");
    }

    public void launchBrowser() {
        System.setProperty("java.awt.headless", "false");
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI("http://localhost:8080/swagger-ui.html"));
            desktop.browse(new URI("http://localhost:8080/h2"));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}