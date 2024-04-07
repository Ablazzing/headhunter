package org.javaacademy.headhunter.resume;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class ResumeService {
    private UserRepository userRepository;

    public ResumeService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(String email, String name) {
        User user = new User(name, email);
        userRepository.save(user);
    }

    public void createResume(String email, BigDecimal salary, String skillsDescription) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not exists"));
        user.setResume(new Resume(salary, skillsDescription));
    }

    public List<Resume> findAllResume() {
        return userRepository.findAll().stream().map(User::getResume).filter(Objects::nonNull)
                .toList();
    }
}
