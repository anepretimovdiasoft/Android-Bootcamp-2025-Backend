package com.example.bootcamp.controller;



import com.example.bootcamp.model.VolunteerCenter;
import com.example.bootcamp.repository.VolunteerCenterRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/centers")
public class VolunteerCenterController {

    private final VolunteerCenterRepository centerRepository;

    public VolunteerCenterController(VolunteerCenterRepository centerRepository) {
        this.centerRepository = centerRepository;
    }

    @GetMapping
    public ResponseEntity<List<VolunteerCenter>> getAllCenters() {
        return ResponseEntity.ok(centerRepository.findAll());
    }
}