package com.project.api.rest.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableCaching
@SpringBootApplication(scanBasePackages = {"com.project.api.rest.model", "com.project.api.rest.server", "com.project.api.rest.service"})
@EntityScan(basePackages = {"com.project.api.rest.model", "com.project.api.rest.server", "com.project.api.rest.service"})
@EnableJpaRepositories(basePackages = {"com.project.api.rest.service.repository"})
public class ServerApplication {
    private static final Logger logger = LoggerFactory.getLogger(ServerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
        logger.info("Hello World!, Rest Api Application just started up");
    }
}