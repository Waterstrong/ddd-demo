package com.water.demo.ddd.domain.user.model;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class User {
    private String uuid = UUID.randomUUID().toString();
    private String email;
    private String password;
    private String policyNumber;

    public void initPassword(String password) {
        this.password = password;
    }

    public boolean matchPassword(String password) {
        return password.equals(this.password);
    }
}
