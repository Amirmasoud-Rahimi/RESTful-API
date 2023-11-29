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
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    public void startupListener() throws InterruptedException, URISyntaxException, IOException {
        logger.info("please wait.Data is fetching from url and saving in h2 db using ExecutorService");
        List<Callable<Void>> taskList = getCallables();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.invokeAll(taskList);
        logger.info("save data completed");
        launchBrowser();
    }

    private List<Callable<Void>> getCallables() {
        Callable<Void> savePostsThread = () -> {
            postService.savePostList();
            return null;
        };
        Callable<Void> saveCommentsThread = () -> {
            commentService.saveCommentList();
            return null;
        };
        Callable<Void> saveToDosThread = () -> {
            toDoService.saveToDoList();
            return null;
        };

        List<Callable<Void>> taskList = new ArrayList<>();
        taskList.add(savePostsThread);
        taskList.add(saveCommentsThread);
        taskList.add(saveToDosThread);

        return taskList;
    }

    public void launchBrowser() throws URISyntaxException, IOException {
        System.setProperty("java.awt.headless", "false");
        Desktop desktop = Desktop.getDesktop();
        desktop.browse(new URI("http://localhost:8080/swagger-ui.html"));
        desktop.browse(new URI("http://localhost:8080/h2"));
    }
}