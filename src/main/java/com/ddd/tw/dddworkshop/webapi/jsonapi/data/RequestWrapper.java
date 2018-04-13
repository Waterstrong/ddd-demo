package com.ddd.tw.dddworkshop.webapi.jsonapi.data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RequestWrapper<T> {
    @Valid
    @NotNull(message = "Resource data field is required")
    private T data;
}
