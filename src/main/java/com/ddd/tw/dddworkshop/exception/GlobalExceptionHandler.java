package com.ddd.tw.dddworkshop.exception;

import static com.google.common.collect.ImmutableList.of;
import static java.lang.String.valueOf;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.ddd.tw.dddworkshop.exception.error.ApiError;
import com.ddd.tw.dddworkshop.exception.error.ApiErrors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleException(ApiException ex) {
        ApiError apiError = ApiError.builder()
                .status(valueOf(ex.getStatus())).code(ex.getCode())
                .title("API Error").detail(ex.getMessage()).build();
        return new ResponseEntity<>(new ApiErrors(of(apiError)), ex.getStatus());
    }
}
