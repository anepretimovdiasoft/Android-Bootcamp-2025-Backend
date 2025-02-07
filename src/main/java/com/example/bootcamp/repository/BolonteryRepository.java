package com.example.bootcamp.repository;

import com.example.bootcamp.entity.Bolontery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BolonteryRepository extends JpaRepository<Bolontery, Long> {
    Bolontery findByEmail(String email);
}