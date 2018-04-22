package com.ddd.tw.dddworkshop.user;

import static com.ddd.tw.dddworkshop.utils.constant.Constants.CONTENT_TYPE;
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
import com.ddd.tw.dddworkshop.user.command.InitPasswordCommand;
import com.ddd.tw.dddworkshop.user.command.LoginCommand;
import com.ddd.tw.dddworkshop.user.command.RegisterCommand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/user", consumes = CONTENT_TYPE, produces = CONTENT_TYPE)
@Api(tags = "User", description = "User Resource", consumes = CONTENT_TYPE, produces = CONTENT_TYPE)
public class UserController {

    @Autowired
    private UserApplicationService applicationService;

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation(value = "Register User", notes = "This is for user register")
    public void register(@Valid @RequestBody RegisterCommand registerField) {
        applicationService.register(registerField);
    }

    @PostMapping("/login")
    @ResponseStatus(OK)
    @ApiOperation(value = "User Login", notes = "This is for user login")
    public void login(@Valid @RequestBody LoginCommand loginField) {
        applicationService.login(loginField);
    }

    @PutMapping("/password")
    @ResponseStatus(OK)
    @ApiOperation(value = "Init Password", notes = "This is for password initial")
    public void initPassword(@Valid @RequestBody InitPasswordCommand initPasswordField) {
        applicationService.initPassword(initPasswordField);
    }
}
