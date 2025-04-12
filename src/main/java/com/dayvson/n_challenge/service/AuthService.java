package com.dayvson.n_challenge.service;

import com.dayvson.n_challenge.dto.AuthRequest;
import com.dayvson.n_challenge.security.JwtUtil;
import com.dayvson.n_challenge.model.User;
import com.dayvson.n_challenge.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public String authenticate(AuthRequest request) {
        User user = userRepository.findAll().stream()
                .filter(u -> u.getEmail().equals(request.getEmail())
                        && u.getPassword().equals(request.getPassword()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        return jwtUtil.generateToken(user.getEmail());
    }
}
