package com.project.api.rest.model.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}