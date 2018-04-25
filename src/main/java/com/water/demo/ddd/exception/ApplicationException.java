package com.water.demo.ddd.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class ApplicationException extends RuntimeException {
    private final HttpStatus status;
    private final String message;
}
