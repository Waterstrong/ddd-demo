package com.water.demo.ddd.exception.common;

import static org.springframework.http.HttpStatus.NOT_FOUND;

public abstract class ResourceNotFoundException extends ApplicationException {
    public ResourceNotFoundException(String code, String message) {
        super(NOT_FOUND, code, message);
    }
}
