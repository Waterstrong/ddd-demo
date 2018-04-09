package com.ddd.tw.dddworkshop.exception;

import static com.google.common.collect.ImmutableList.of;
import static java.lang.String.valueOf;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.ddd.tw.dddworkshop.jsonapi.error.ApiError;
import com.ddd.tw.dddworkshop.jsonapi.error.ApiErrors;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final String API_ERROR = "API Error";

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleException(ApiException ex) {
        ApiError apiError = ApiError.builder()
                .status(valueOf(ex.getStatus())).code(ex.getCode())
                .title(API_ERROR).detail(ex.getMessage()).build();
        return new ResponseEntity<>(new ApiErrors(of(apiError)), ex.getStatus());
    }
}
