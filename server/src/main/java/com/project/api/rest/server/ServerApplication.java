package com.project.api.rest.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.*;
import java.net.URISyntaxException;

@SpringBootApplication(scanBasePackages = {"com.project.api.rest.model", "com.project.api.rest.server", "com.project.api.rest.service"})
@EntityScan(basePackages = {"com.project.api.rest.model", "com.project.api.rest.server", "com.project.api.rest.service"})
@EnableJpaRepositories(basePackages = {"com.project.api.rest.service.repository"})
public class ServerApplication {

    public static void main(String[] args) throws URISyntaxException, IOException {
        SpringApplication.run(ServerApplication.class, args);
    }
}