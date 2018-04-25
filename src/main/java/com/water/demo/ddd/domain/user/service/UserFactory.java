package com.water.demo.ddd.domain.user.service;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.water.demo.ddd.domain.policy.model.Policy;
import com.water.demo.ddd.domain.policy.repository.PolicyRepository;
import com.water.demo.ddd.domain.user.command.RegisterCommand;
import com.water.demo.ddd.domain.user.mapper.UserMapper;
import com.water.demo.ddd.domain.user.model.User;
import com.water.demo.ddd.domain.user.repository.UserRepository;
import com.water.demo.ddd.exception.BadRequestException;
import com.water.demo.ddd.exception.ResourceConflictException;
import com.water.demo.ddd.exception.ResourceNotFoundException;

@Service
public class UserFactory {
    private static final String INCORRECT_EMAIL_MESSAGE = "Incorrect email <%s>!";
    private static final String EMAIL_REGISTERED_MESSAGE = "Email <%s> already registered!";
    private static final String POLICY_NOT_FOUND_MESSAGE = "Policy <%s> not found!";

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private UserRepository userRepository;

    public User createUser(RegisterCommand command) {
        checkRegisterCondition(command);

        User user = UserMapper.INSTANCE.mapToUser(command);

        emailService.sendEmail(user.getUuid());

        return user;
    }

    private void checkRegisterCondition(RegisterCommand command) {
        String policyNumber = command.getPolicyNumber();
        String email = command.getEmail();

        Policy policy = ofNullable(policyRepository.byPolicyNumber(policyNumber))
                .orElseThrow(() -> newPolicyNotFoundException(policyNumber));

        if (!email.equals(policy.getHolderDetail().getEmail())) {
            throw new BadRequestException(format(INCORRECT_EMAIL_MESSAGE, email));
        }

        if (userRepository.byEmail(email) != null) {
            throw new ResourceConflictException(format(EMAIL_REGISTERED_MESSAGE, email));
        }
    }

    private ResourceNotFoundException newPolicyNotFoundException(String policyNumber) {
        return new ResourceNotFoundException(format(POLICY_NOT_FOUND_MESSAGE, policyNumber));
    }
}
