package com.example.bootcamp.controller;

import com.example.bootcamp.dto.VolunteerCenterDTO;
import com.example.bootcamp.service.VolunteerCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;

@RestController
@RequestMapping("/volunteer-centers")
@RequiredArgsConstructor
public class VolunteerCenterController {

    private final VolunteerCenterService volunteerCenterService;

    @GetMapping
    public ResponseEntity<List<VolunteerCenterDTO>> getAllVolunteerCenters() {
        return ResponseEntity.ok(volunteerCenterService.getAllVolunteerCenters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VolunteerCenterDTO> getVolunteerCenterById(@PathVariable Long id) {
        return ResponseEntity.ok(volunteerCenterService.getVolunteerCenterById(id));
    }

    @PostMapping
    public ResponseEntity<VolunteerCenterDTO> createVolunteerCenter(@RequestBody VolunteerCenterDTO volunteerCenterDTO) {
        return ResponseEntity.ok(volunteerCenterService.createVolunteerCenter(volunteerCenterDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VolunteerCenterDTO> updateVolunteerCenter(@PathVariable Long id, @RequestBody VolunteerCenterDTO volunteerCenterDTO) {
        return ResponseEntity.ok(volunteerCenterService.updateVolunteerCenter(id, volunteerCenterDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVolunteerCenter(@PathVariable Long id) {
        volunteerCenterService.deleteVolunteerCenter(id);
        return ResponseEntity.noContent().build();
    }
}
