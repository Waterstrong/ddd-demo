package com.water.demo.ddd.exception.common;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApplicationException extends RuntimeException {
    private final HttpStatus status;
    private final String code;
    private final String message;
}
