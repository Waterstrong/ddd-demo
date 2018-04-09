package com.ddd.tw.dddworkshop.policy.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PolicyApplicationService {
    public double calculateQuote() {
        return 0;
    }

    @Transactional
    public boolean confirmPolicy() {
        return false;
    }
}
