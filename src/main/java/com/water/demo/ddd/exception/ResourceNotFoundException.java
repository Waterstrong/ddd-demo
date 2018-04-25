package com.water.demo.ddd.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public class ResourceNotFoundException extends ApplicationException {
    public ResourceNotFoundException(String message) {
        super(NOT_FOUND, message);
    }
}
