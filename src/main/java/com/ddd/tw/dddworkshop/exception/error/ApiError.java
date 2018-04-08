package com.ddd.tw.dddworkshop.exception.error;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_EMPTY;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(NON_EMPTY)
public class ApiError {
    private String status;
    private String code;
    private String title;
    private String detail;
}
