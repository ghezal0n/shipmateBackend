package com.example.cost.dto;

public class AuthResponse {
    private String token;
    private String role;  // ← NEW: expose role to frontend

    public AuthResponse(String token, String role) {
        this.token = token;
        this.role = role;
    }

    public String getToken() { return token; }
    public String getRole()  { return role;  }
}