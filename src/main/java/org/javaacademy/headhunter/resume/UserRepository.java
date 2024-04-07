package org.javaacademy.headhunter.resume;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class UserRepository {
    private final Map<String, User> userMap = new HashMap<>();

    public void save(User user) {
        if (userMap.containsKey(user.getEmail())) {
            throw new RuntimeException("User already exists");
        }
        userMap.put(user.getEmail(), user);
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userMap.get(email));
    }

    public Collection<User> findAll() {
        return userMap.values();
    }
}
