package com.ddd.tw.dddworkshop.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiException extends RuntimeException {
    private final HttpStatus status;
    private final String code;
    private final String message;
}
