package com.ddd.tw.dddworkshop.user.command;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegisterCommand {
    private String email;
    private String policyNumber;
}
