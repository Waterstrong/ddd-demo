package com.ddd.tw.dddworkshop.user.command;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginCommand {
    private String email;
    private String password;
}
