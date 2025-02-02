package com.example.bootcamp.service;

import com.example.bootcamp.dto.auth.JetResponse;
import com.example.bootcamp.dto.auth.JwtRequest;

public interface AuthService {
    JetResponse login(JwtRequest loginRequest);
    JetResponse refresh(String refreshToken);
}
