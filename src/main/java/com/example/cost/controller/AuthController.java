package com.example.cost.controller;

import com.example.cost.dto.*;
import com.example.cost.entity.User;
import com.example.cost.service.AuthService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return authService.register(user);
    }

    @PutMapping("/updatePassword")
    public User updatePassword(@AuthenticationPrincipal User user,
                               @RequestBody UpdatePasswordRequest req) {
        return authService.updatePassword(user.getEmail(), req);
    }

    @PutMapping("/updateInfo")
    public User updateInfo(@AuthenticationPrincipal User user,
                           @RequestBody UpdateUserRequest req) {
        return authService.updateInfo(user.getEmail(), req);
    }
}
