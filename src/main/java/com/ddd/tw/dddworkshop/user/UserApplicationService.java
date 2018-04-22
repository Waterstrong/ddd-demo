package com.ddd.tw.dddworkshop.user;

import static java.util.Optional.ofNullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ddd.tw.dddworkshop.exception.UserNotFoundException;
import com.ddd.tw.dddworkshop.user.command.InitPasswordCommand;
import com.ddd.tw.dddworkshop.user.command.LoginCommand;
import com.ddd.tw.dddworkshop.user.command.RegisterCommand;
import com.ddd.tw.dddworkshop.user.model.User;
import com.ddd.tw.dddworkshop.user.repository.UserRepository;
import com.ddd.tw.dddworkshop.user.service.LoginService;
import com.ddd.tw.dddworkshop.user.service.UserFactory;

@Service
@Transactional
public class UserApplicationService {
    @Autowired
    private UserFactory userFactory;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LoginService loginService;

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
                .orElseThrow(() -> new UserNotFoundException(uuid));

        user.initPassword(command.getPassword());

        userRepository.save(user);
    }
}
