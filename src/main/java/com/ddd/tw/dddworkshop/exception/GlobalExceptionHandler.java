package com.ddd.tw.dddworkshop.exception;

import static com.google.common.collect.ImmutableList.of;
import static java.lang.String.format;
import static java.lang.String.valueOf;
import static java.util.stream.Collectors.toList;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.ddd.tw.dddworkshop.jsonapi.error.ApiError;
import com.ddd.tw.dddworkshop.jsonapi.error.ApiErrors;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String API_ERROR = "API Error";

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ApiErrors> handleException(DomainException ex) {
        ApiError apiError = newApiError(ex.getStatus(), ex.getCode(), ex.getMessage());
        return new ResponseEntity<>(new ApiErrors(of(apiError)), ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrors> handleException(MethodArgumentNotValidException e) {
        List<ApiError> apiErrors = e.getBindingResult().getFieldErrors().stream()
                .map(field -> newApiError(BAD_REQUEST, "009", format("%s %s", field.getField(), field.getDefaultMessage())))
                .collect(toList());
        return new ResponseEntity<>(new ApiErrors(apiErrors), BAD_REQUEST);
    }

    private ApiError newApiError(HttpStatus status, String code, String message) {
        return ApiError.builder()
                .status(valueOf(status))
                .code(code)
                .title(API_ERROR)
                .detail(message).build();
    }
}
