package com.example.bootcamp.service.impl;

import com.example.bootcamp.aspect.annotation.LogExample;
import com.example.bootcamp.service.AuthService;
import com.example.bootcamp.dto.auth.JetResponse;
import com.example.bootcamp.dto.auth.JwtRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    @Override
    @LogExample
    public JetResponse login(JwtRequest loginRequest) {
        return null;
    }

    @Override
    @LogExample
    public JetResponse refresh(String refreshToken) {
        return null;
    }
}
