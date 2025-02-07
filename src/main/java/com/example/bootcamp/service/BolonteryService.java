package com.example.bootcamp.service;

import com.example.bootcamp.entity.Bolontery;
import com.example.bootcamp.repository.BolonteryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class BolonteryService {

    @Autowired
    private BolonteryRepository bolonteryRepository;

    public Bolontery register(Bolontery bolontery) {
        // Проверка на уникальность email, хэширование пароля и другие бизнес-правила
        return bolonteryRepository.save(bolontery);
    }

    public Bolontery update(Long id, Bolontery bolontery) {
        // Пример обновления профиля волонтера
        Bolontery existingBolontery = bolonteryRepository.findById(id).orElseThrow(() -> new RuntimeException("Bolontery not found"));
        existingBolontery.setName(bolontery.getName());
        existingBolontery.setPhoneNumber(bolontery.getPhoneNumber());
        return bolonteryRepository.save(existingBolontery);
    }

    public List<Bolontery> getAllBolonteries() {
        return bolonteryRepository.findAll();
    }
}