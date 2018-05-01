package com.water.demo.ddd.domain.user;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.water.demo.ddd.domain.user.command.LoginCommand;
import com.water.demo.ddd.domain.user.model.User;
import com.water.demo.ddd.domain.user.repository.UserRepository;
import com.water.demo.ddd.domain.user.service.RegisterService;
import com.water.demo.ddd.exception.ResourceNotFoundException;

@Service
@Transactional
public class UserApplicationService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RegisterService registerService;


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

    public void login(LoginCommand loginField) {

    }
}
