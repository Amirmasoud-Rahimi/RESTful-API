package com.project.api.rest.server;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.project.api.rest.model.Post;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) throws URISyntaxException, IOException {
        SpringApplication.run(ServerApplication.class, args);
        URL url = new URL("https://jsonplaceholder.typicode.com/posts");
        String json = IOUtils.toString(url, StandardCharsets.UTF_8);
        ObjectMapper mapper=new ObjectMapper();
        List<Post> participantJsonList = mapper.readValue(json, new TypeReference<List<Post>>(){});


    }
}