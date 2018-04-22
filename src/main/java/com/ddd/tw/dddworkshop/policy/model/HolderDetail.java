package com.ddd.tw.dddworkshop.policy.model;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HolderDetail {
    @NotBlank
    private String id;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String dateOfBirth;
}
