package com.project.api.rest.server.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Value("${api.title}")
    private String apiTitle;

    @Value("${api.description}")
    private String apiDescription;

    @Value("${api.name}")
    private String apiName;

    @Value("${api.url}")
    private String apiUrl;

    @Value("${api.email}")
    private String apiEmail;

    @Value("${api.version}")
    private String apiVersion;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().apiInfo(apiEndPointInfo());
    }

    private ApiInfo apiEndPointInfo() {
        return new ApiInfoBuilder().title(apiTitle)
                .description(apiDescription)
                .contact(new Contact(apiName, apiUrl, apiEmail))
                .version(apiVersion)
                .build();
    }
}