package com.ddd.tw.dddworkshop.webapi.jsonapi.data;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseWrapper<T> {
    private T data;
    private List<Error> errors;
}
