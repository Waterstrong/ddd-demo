package com.water.demo.ddd.user.service;

import static java.lang.String.format;

import org.springframework.stereotype.Service;

@Service
public class EmailService {
    public void sendEmail(String uuid) {
        System.out.println("Email Title/Subject/Content: $#@^$#@%$#@$#");
        System.out.println(format("UUID: %s", uuid));
    }
}
