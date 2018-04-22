package com.ddd.tw.dddworkshop.user.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.ddd.tw.dddworkshop.user.model.User;

@Repository
public class UserRepository {
    private Map<String, User> userMap = new HashMap<>();

    public void save(User user) {
        userMap.put(user.getUuid(), user);
    }

    public User byUuid(String uuid) {
        return userMap.get(uuid);
    }

    public User byEmail(String email) {
        return userMap.values().stream()
                .filter(user -> email.equals(user.getEmail()))
                .findFirst()
                .orElse(null);
    }
}
