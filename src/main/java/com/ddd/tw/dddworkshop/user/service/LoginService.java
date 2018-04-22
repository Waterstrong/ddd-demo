package com.ddd.tw.dddworkshop.user.service;

import static java.util.Optional.ofNullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ddd.tw.dddworkshop.exception.LoginFailedException;
import com.ddd.tw.dddworkshop.exception.UserNotFoundException;
import com.ddd.tw.dddworkshop.user.model.User;
import com.ddd.tw.dddworkshop.user.repository.UserRepository;

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
