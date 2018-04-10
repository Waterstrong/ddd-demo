package com.ddd.tw.dddworkshop.policy.application;

import com.ddd.tw.dddworkshop.policy.domain.PolicyHolder;
import com.ddd.tw.dddworkshop.policy.domain.Quotation;

public interface PolicyApplicationService<T> {
    Quotation calculateQuote(T policyCommand);
    String generatePolicy(T policyCommand, PolicyHolder policyHolder);
}
