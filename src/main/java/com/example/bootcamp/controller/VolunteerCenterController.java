package com.example.bootcamp.controller;

import com.example.bootcamp.dto.VolunteerCenterDTO;
import com.example.bootcamp.service.VolunteerCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/volunteer_center")
@RequiredArgsConstructor
public class VolunteerCenterController {
    private final VolunteerCenterService volunteerCenterService;

    @GetMapping("/{id}")
    public ResponseEntity<VolunteerCenterDTO> getVolunteerCenterById(@PathVariable Long id) {
        return ResponseEntity.ok(volunteerCenterService.getVolunteerCenterById(id));
    }

    @GetMapping()
    public List<VolunteerCenterDTO> getAllVolunteerCenter() {
        return volunteerCenterService.getAllVolunteerCenter();
    }
}
