package com.water.demo.ddd.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class BadRequestException extends ApplicationException {
    public BadRequestException(String message) {
        super(BAD_REQUEST, message);
    }
}
