package com.example.bootcamp.controller;


import com.example.bootcamp.entity.Bolontery;
import com.example.bootcamp.service.BolonteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Bolonteries")
public class BolonteryController {

    @Autowired
    private BolonteryService bolonteryService;

    @PostMapping("/register")
    public ResponseEntity<Bolontery> register(@RequestBody Bolontery bolontery) {
        // Без валидации данных
        return ResponseEntity.ok(bolonteryService.register(bolontery));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Bolontery bolontery) {
        // Логика авторизации (не реализована в этой задаче)
        return ResponseEntity.ok("Logged in successfully");
    }

    @GetMapping
    public ResponseEntity<List<Bolontery>> getAllBolonteries() {
        return ResponseEntity.ok(bolonteryService.getAllBolonteries());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Bolontery> update(@PathVariable Long id, @RequestBody Bolontery bolontery) {
        // Без валидации данных
        return ResponseEntity.ok(bolonteryService.update(id, bolontery));
    }
}