package com.water.demo.ddd.domain.user;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.water.demo.ddd.domain.user.command.InitPasswordCommand;
import com.water.demo.ddd.domain.user.command.LoginCommand;
import com.water.demo.ddd.domain.user.command.RegisterCommand;
import com.water.demo.ddd.domain.user.model.User;
import com.water.demo.ddd.domain.user.repository.UserRepository;
import com.water.demo.ddd.domain.user.service.LoginService;
import com.water.demo.ddd.domain.user.service.RegisterService;
import com.water.demo.ddd.domain.user.service.UserFactory;
import com.water.demo.ddd.exception.ResourceNotFoundException;

@Service
@Transactional
public class UserApplicationService {
    @Autowired
    private UserFactory userFactory;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginService loginService;

    @Autowired
    private RegisterService registerService;

    public void register(RegisterCommand command) {
        User user = userFactory.createUser(command);

        userRepository.save(user);
    }

    public void login(LoginCommand command) {
        loginService.login(command.getEmail(), command.getPassword());
    }

    public void initPassword(InitPasswordCommand command) {
        String uuid = command.getUuid();

        User user = ofNullable(userRepository.byUuid(uuid))
                .orElseThrow(() -> new ResourceNotFoundException(format("User <%s> not found!", uuid)));

        user.initPassword(command.getPassword());

        userRepository.save(user);
    }

    public String register(String email, String policyNumber) {
        User user = registerService.register(email, policyNumber);

        userRepository.save(user);

        return user.getUuid();
    }

    public void initPassword(String uuid, String password) {
        User user = ofNullable(userRepository.byUuid(uuid))
                .orElseThrow(() -> new ResourceNotFoundException(format("User <%s> not found!", uuid)));

        user.initPassword(password);

        userRepository.save(user);
    }
}
