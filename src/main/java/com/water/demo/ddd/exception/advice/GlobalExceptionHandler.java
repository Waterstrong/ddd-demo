package com.water.demo.ddd.exception.advice;

import static com.google.common.collect.ImmutableList.of;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.water.demo.ddd.exception.ApplicationException;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String ERROR_MESSAGE_FORMAT = "%s %s";

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiErrors> handleException(ApplicationException ex) {
        ApiErrors apiErrors = new ApiErrors(of(newApiError(ex)));
        return new ResponseEntity<>(apiErrors, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrors> handleException(MethodArgumentNotValidException e) {
        List<ApiError> apiErrors = e.getBindingResult().getFieldErrors().stream()
                .map(this::newApiError)
                .collect(toList());
        return new ResponseEntity<>(new ApiErrors(apiErrors), BAD_REQUEST);
    }

    private ApiError newApiError(FieldError field) {
        String message = format(ERROR_MESSAGE_FORMAT, field.getField(), field.getDefaultMessage());
        return newApiError(BAD_REQUEST, BAD_REQUEST.toString(), message);
    }

    private ApiError newApiError(ApplicationException ex) {
        return newApiError(ex.getStatus(), ex.getStatus().toString(), ex.getMessage());
    }

    private ApiError newApiError(HttpStatus status, String title, String message) {
        return ApiError.builder()
                .status(valueOf(status))
                .title(title)
                .detail(message).build();
    }
}
