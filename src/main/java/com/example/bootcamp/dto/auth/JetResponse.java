package com.example.bootcamp.dto.auth;

import lombok.Data;

@Data
public class JetResponse {
    private long id;
    private String email;
    private String accessToken;
    private String refreshToken;
}
