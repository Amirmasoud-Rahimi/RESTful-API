package com.project.api.rest.server.exceptionhandler;

import com.project.api.rest.model.exception.ApiExceptionMessage;
import com.project.api.rest.model.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpStatusCodeException;

@ControllerAdvice
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ExceptionHandlerAdvice {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiExceptionMessage> exceptionHandler(Exception exception) {
        ApiExceptionMessage apiExceptionMessage;
        HttpStatus httpStatus;

        try {
            httpStatus = ((HttpStatusCodeException) exception).getStatusCode();
        } catch (Exception e) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        apiExceptionMessage = ApiExceptionMessage.builder()
                .exceptionType(exception.getClass().getName())
                .message(exception.getMessage()).build();
        return ResponseEntity.status(httpStatus).body(apiExceptionMessage);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiExceptionMessage> apiExceptionHandler(EntityNotFoundException entityNotFoundException) {
        ApiExceptionMessage apiExceptionMessage = ApiExceptionMessage.builder()
                .exceptionType(entityNotFoundException.getClass().getName())
                .message(entityNotFoundException.getMessage()).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiExceptionMessage);
    }
}