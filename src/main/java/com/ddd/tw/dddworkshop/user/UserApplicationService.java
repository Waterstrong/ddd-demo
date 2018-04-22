package com.ddd.tw.dddworkshop.user;

import org.springframework.stereotype.Component;
import com.ddd.tw.dddworkshop.user.command.InitPasswordCommand;
import com.ddd.tw.dddworkshop.user.command.LoginCommand;
import com.ddd.tw.dddworkshop.user.command.RegisterCommand;

@Component
public class UserApplicationService {
    public void register(RegisterCommand command) {

    }

    public void login(LoginCommand command) {

    }

    public void initPassword(InitPasswordCommand command) {

    }
}
