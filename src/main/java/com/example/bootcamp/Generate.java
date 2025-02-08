package com.example.bootcamp;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Generate {
    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "admin";


        String hashedPassword = passwordEncoder.encode(rawPassword);

        System.out.println("Хеш пароля: " + hashedPassword);
    }
}
