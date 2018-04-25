package com.water.demo.ddd.user;

import static java.util.Optional.ofNullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.water.demo.ddd.exception.UserNotFoundException;
import com.water.demo.ddd.user.command.InitPasswordCommand;
import com.water.demo.ddd.user.command.LoginCommand;
import com.water.demo.ddd.user.command.RegisterCommand;
import com.water.demo.ddd.user.model.User;
import com.water.demo.ddd.user.repository.UserRepository;
import com.water.demo.ddd.user.service.LoginService;
import com.water.demo.ddd.user.service.UserFactory;

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
