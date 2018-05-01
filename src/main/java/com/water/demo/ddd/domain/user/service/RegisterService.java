package com.water.demo.ddd.domain.user.service;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.water.demo.ddd.domain.policy.model.Policy;
import com.water.demo.ddd.domain.policy.repository.PolicyRepository;
import com.water.demo.ddd.domain.user.model.User;
import com.water.demo.ddd.domain.user.repository.UserRepository;
import com.water.demo.ddd.exception.BadRequestException;
import com.water.demo.ddd.exception.ResourceConflictException;
import com.water.demo.ddd.exception.ResourceNotFoundException;

@Service
public class RegisterService {
    private static final String INCORRECT_EMAIL_MESSAGE = "Incorrect email <%s>!";
    private static final String EMAIL_REGISTERED_MESSAGE = "Email <%s> already registered!";
    private static final String POLICY_NOT_FOUND_MESSAGE = "Policy <%s> not found!";

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private UserRepository userRepository;

    public User register(String email, String policyNumber) {
        checkRegisterCondition(email, policyNumber);

        return new User(email);
    }

    private void checkRegisterCondition(String email, String policyNumber) {
        Policy policy = retrievePolicy(policyNumber);

        if (!email.equals(policy.getHolderDetail().getEmail())) {
            throw new BadRequestException(format(INCORRECT_EMAIL_MESSAGE, email));
        }

        if (userRepository.byEmail(email) != null) {
            throw new ResourceConflictException(format(EMAIL_REGISTERED_MESSAGE, email));
        }
    }

    private Policy retrievePolicy(String policyNumber) {
        return ofNullable(policyRepository.byPolicyNumber(policyNumber))
                .orElseThrow(() -> new ResourceNotFoundException(format(POLICY_NOT_FOUND_MESSAGE, policyNumber)));
    }

}
