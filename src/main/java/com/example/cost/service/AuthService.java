package com.example.cost.service;

import com.example.cost.dto.*;
import com.example.cost.entity.User;
import com.example.cost.repository.UserRepository;
import com.example.cost.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtService jwtService,
                       AuthenticationManager authenticationManager,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        // ── NEW: include role in generated token ──────────────────
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.generateToken(request.getEmail(), user.getRole());
        // ─────────────────────────────────────────────────────────

        return new AuthResponse(token, user.getRole());
    }

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // role defaults to "USER" (set in User entity field initializer)
        return userRepository.save(user);
    }

    public User updatePassword(String email, UpdatePasswordRequest req) {
        User user = userRepository.findByEmail(email).orElseThrow();
        if (!passwordEncoder.matches(req.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Ancien mot de passe incorrect");
        }
        user.setPassword(passwordEncoder.encode(req.getNewPassword()));
        return userRepository.save(user);
    }

    public User updateInfo(String email, UpdateUserRequest req) {
        User user = userRepository.findByEmail(email).orElseThrow();
        user.setEmail(req.getEmail());
        user.setUsername(req.getUsername());
        return userRepository.save(user);
    }
}