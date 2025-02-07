package com.example.bootcamp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id; // <-- Важно! Из пакета jakarta.persistence
import lombok.Data;

@Entity
@Data
public class Center {
    @Id  // <-- Теперь правильный импорт
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
}
