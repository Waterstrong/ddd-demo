package com.water.demo.ddd.user.service;

import static java.util.Optional.ofNullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.water.demo.ddd.exception.EmailAlreadyRegistered;
import com.water.demo.ddd.exception.IncorrectEmailException;
import com.water.demo.ddd.exception.PolicyNotFoundException;
import com.water.demo.ddd.policy.model.Policy;
import com.water.demo.ddd.policy.repository.PolicyRepository;
import com.water.demo.ddd.user.command.RegisterCommand;
import com.water.demo.ddd.user.mapper.UserMapper;
import com.water.demo.ddd.user.model.User;
import com.water.demo.ddd.user.repository.UserRepository;

@Service
public class UserFactory {
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
                .orElseThrow(() -> new PolicyNotFoundException(policyNumber));

        if (!email.equals(policy.getHolderDetail().getEmail())) {
            throw new IncorrectEmailException(email);
        }

        if (userRepository.byEmail(email) != null) {
            throw new EmailAlreadyRegistered(email);
        }
    }
}
