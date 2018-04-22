package com.ddd.tw.dddworkshop.user.service;

import static com.ddd.tw.dddworkshop.user.mapper.UserMapper.INSTANCE;
import static java.util.Optional.ofNullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ddd.tw.dddworkshop.exception.EmailAlreadyRegistered;
import com.ddd.tw.dddworkshop.exception.IncorrectEmailException;
import com.ddd.tw.dddworkshop.exception.PolicyNotFoundException;
import com.ddd.tw.dddworkshop.policy.model.Policy;
import com.ddd.tw.dddworkshop.policy.repository.PolicyRepository;
import com.ddd.tw.dddworkshop.user.command.RegisterCommand;
import com.ddd.tw.dddworkshop.user.model.User;
import com.ddd.tw.dddworkshop.user.repository.UserRepository;

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

        User user = INSTANCE.mapToUser(command);

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
