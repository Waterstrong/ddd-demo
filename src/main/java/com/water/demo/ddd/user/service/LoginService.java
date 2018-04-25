package com.water.demo.ddd.user.service;

import static java.util.Optional.ofNullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.water.demo.ddd.exception.LoginFailedException;
import com.water.demo.ddd.exception.UserNotFoundException;
import com.water.demo.ddd.user.model.User;
import com.water.demo.ddd.user.repository.UserRepository;

@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    public void login(String email, String password) {
        User user = ofNullable(userRepository.byEmail(email))
                .orElseThrow(() -> new UserNotFoundException(email));
        if (!user.matchPassword(password)) {
            throw new LoginFailedException();
        }
    }
}
