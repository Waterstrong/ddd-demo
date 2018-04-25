package com.water.demo.ddd.domain.user.service;

import static java.lang.String.format;
import static java.util.Optional.ofNullable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.water.demo.ddd.domain.user.model.User;
import com.water.demo.ddd.domain.user.repository.UserRepository;
import com.water.demo.ddd.exception.ResourceNotFoundException;
import com.water.demo.ddd.exception.UnauthorizedException;

@Service
public class LoginService {
    private static final String USER_NOT_FOUND_MESSAGE = "User <%s> not found!";
    private static final String LOGIN_FAILED_MESSAGE = "Password incorrect or not init! Login failed!";

    @Autowired
    private UserRepository userRepository;

    public void login(String email, String password) {
        User user = ofNullable(userRepository.byEmail(email))
                .orElseThrow(() -> userNotFoundException(email));

        if (!user.matchPassword(password)) {
            throw new UnauthorizedException(LOGIN_FAILED_MESSAGE);
        }
    }

    private ResourceNotFoundException userNotFoundException(String email) {
        return new ResourceNotFoundException(format(USER_NOT_FOUND_MESSAGE, email));
    }
}
