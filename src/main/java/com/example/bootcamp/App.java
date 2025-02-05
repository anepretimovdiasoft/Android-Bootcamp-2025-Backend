package com.example.bootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        String p1 = bc.encode("password");
        System.out.println(p1);

    }
}
