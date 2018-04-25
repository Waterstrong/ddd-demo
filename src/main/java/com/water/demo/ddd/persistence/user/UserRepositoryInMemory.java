package com.water.demo.ddd.persistence.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.water.demo.ddd.domain.user.model.User;
import com.water.demo.ddd.domain.user.repository.UserRepository;

@Repository
public class UserRepositoryInMemory implements UserRepository {
    private Map<String, User> userMap = new HashMap<>();

    @Override
    public void save(User user) {
        userMap.put(user.getUuid(), user);
    }

    @Override
    public User byUuid(String uuid) {
        return userMap.get(uuid);
    }

    @Override
    public User byEmail(String email) {
        return userMap.values().stream()
                .filter(user -> email.equals(user.getEmail()))
                .findFirst()
                .orElse(null);
    }
}
