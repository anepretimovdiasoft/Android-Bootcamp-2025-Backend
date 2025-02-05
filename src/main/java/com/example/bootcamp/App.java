package com.example.bootcamp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.example.bootcamp")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}