package com.example.promdetal_backend.service;

import com.example.promdetal_backend.entity.User;
import com.example.promdetal_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userRepository.findByUsername("admin@gmail.com").isEmpty()) {
            User user = new User();
            user.setUsername("admin@gmail.com");
            user.setPassword(passwordEncoder.encode("1234"));
            userRepository.save(user);
        }
    }
}
