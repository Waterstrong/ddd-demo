package com.water.demo.ddd.webapi.user;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.water.demo.ddd.domain.user.UserApplicationService;
import com.water.demo.ddd.domain.user.command.InitPasswordCommand;
import com.water.demo.ddd.domain.user.command.LoginCommand;
import com.water.demo.ddd.domain.user.command.RegisterCommand;
import com.water.demo.ddd.utils.constant.Constants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/user", consumes = Constants.CONTENT_TYPE)
@Api(tags = "User", description = "User Resource", consumes = Constants.CONTENT_TYPE)
public class UserController {

    @Autowired
    private UserApplicationService applicationService;

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation(value = "Register User", notes = "This is for user register")
    public String register(@Valid @RequestBody RegisterCommand registerField) {
        return applicationService.register(registerField.getEmail(), registerField.getPolicyNumber());
    }

    @PostMapping("/login")
    @ResponseStatus(OK)
    @ApiOperation(value = "User Login", notes = "This is for user login")
    public void login(@Valid @RequestBody LoginCommand loginField) {
        applicationService.login(loginField.getEmail(), loginField.getPassword());
    }

    @PutMapping("/password")
    @ResponseStatus(OK)
    @ApiOperation(value = "Init Password", notes = "This is for password initial")
    public void initPassword(@Valid @RequestBody InitPasswordCommand initPasswordField) {
        applicationService.initPassword(initPasswordField.getUuid(), initPasswordField.getPassword());
    }
}
