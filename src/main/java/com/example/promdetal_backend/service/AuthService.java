package com.example.promdetal_backend.service;

import com.example.promdetal_backend.JwtUtil;
import com.example.promdetal_backend.dto.LoginRequest;
import com.example.promdetal_backend.dto.LoginResponse;
import com.example.promdetal_backend.entity.User;
import com.example.promdetal_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public LoginResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        LoginResponse response = new LoginResponse();
        response.setToken(jwtUtil.generateToken(user.getUsername()));
        return response;
    }
}

