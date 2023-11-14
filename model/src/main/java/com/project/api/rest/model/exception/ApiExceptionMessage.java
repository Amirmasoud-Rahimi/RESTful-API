package com.project.api.rest.model.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ApiExceptionMessage {
    private String exceptionType;
    private String message;
}