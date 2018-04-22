package com.ddd.tw.dddworkshop.user.model;

import static org.apache.commons.lang3.StringUtils.isNotEmpty;

import java.util.UUID;

import com.ddd.tw.dddworkshop.exception.PasswordInitException;

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
        if (isNotEmpty(this.password)) {
            throw new PasswordInitException();
        }
        this.password = password;
    }

    public boolean matchPassword(String password) {
        return password.equals(this.password);
    }
}
