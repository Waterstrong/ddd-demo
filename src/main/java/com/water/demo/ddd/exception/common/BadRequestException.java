package com.water.demo.ddd.exception.common;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public abstract class BadRequestException extends ApplicationException {
    public BadRequestException(String code, String message) {
        super(BAD_REQUEST, code, message);
    }
}
