package com.water.demo.ddd.domain.user.repository;

import com.water.demo.ddd.domain.user.model.User;

public interface UserRepository {
    void save(User user);

    User byUuid(String uuid);

    User byEmail(String email);
}
