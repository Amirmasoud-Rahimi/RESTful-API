package com.project.api.rest.server.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.awt.Desktop;
import java.net.URI;

@Component
public class BrowserLauncher {
    Logger logger = LoggerFactory.getLogger(BrowserLauncher.class);

    @EventListener(ApplicationReadyEvent.class)
    public void launchBrowser() {
        System.setProperty("java.awt.headless", "false");
        Desktop desktop = Desktop.getDesktop();
        try {
            logger.info("Swagger Page loading...");
            desktop.browse(new URI("http://localhost:8080/swagger-ui.html"));
            logger.info("H2 DB Page loading...");
            desktop.browse(new URI("http://localhost:8080/h2"));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}