package com.ddd.tw.dddworkshop.user.command;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginCommand {
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
