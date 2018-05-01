package com.water.demo.ddd.domain.user;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.water.demo.ddd.domain.user.model.User;
import com.water.demo.ddd.domain.user.repository.UserRepository;
import com.water.demo.ddd.domain.user.service.EmailService;
import com.water.demo.ddd.domain.user.service.LoginService;
import com.water.demo.ddd.domain.user.service.RegisterService;
import com.water.demo.ddd.exception.ResourceNotFoundException;

@Service
@Transactional
public class UserApplicationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegisterService registerService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private LoginService loginService;

    public String register(String email, String policyNumber) {
        User user = registerService.register(email, policyNumber);

        userRepository.save(user);

        emailService.sendEmail(user.getUuid());

        return user.getUuid();
    }

    public void initPassword(String uuid, String password) {
        User user = retrieveUser(uuid);

        user.initPassword(password);

        userRepository.save(user);
    }

    public void login(String email, String password) {
        loginService.login(email, password);
    }

    private User retrieveUser(String uuid) {
        return ofNullable(userRepository.byUuid(uuid))
                .orElseThrow(() -> new ResourceNotFoundException(format("User <%s> not found!", uuid)));
    }
}
