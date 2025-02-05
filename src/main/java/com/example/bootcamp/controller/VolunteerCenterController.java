package com.example.bootcamp.controller;

import com.example.bootcamp.dto.VolunteerCenterDTO;
import com.example.bootcamp.service.VolunteerCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/volunteer-centers")
public class VolunteerCenterController {
    private final VolunteerCenterService volunteerCenterService;

    public VolunteerCenterController(VolunteerCenterService volunteerCenterService) {
        this.volunteerCenterService = volunteerCenterService;
    }
    @GetMapping
    public List<VolunteerCenterDTO> getAllVolunteerCenters() {
        return volunteerCenterService.getAllVolunteerCenters();
    }

    @GetMapping("/{id}")
    public ResponseEntity<VolunteerCenterDTO> getVolunteerCenterById(@PathVariable Long id) {
        return ResponseEntity.ok(volunteerCenterService.getVolunteerCenterById(id));
    }

    @PostMapping
    public VolunteerCenterDTO createVolunteerCenter(@RequestBody VolunteerCenterDTO volunteerCenterDTO) {
        return volunteerCenterService.createVolunteerCenter(volunteerCenterDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VolunteerCenterDTO> updateVolunteerCenter(@PathVariable Long id,
                                                                    @RequestBody VolunteerCenterDTO volunteerCenterDTO) {
        return ResponseEntity.ok(volunteerCenterService.updateVolunteerCenter(id, volunteerCenterDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVolunteerCenter(@PathVariable Long id) {
        volunteerCenterService.deleteVolunteerCenter(id);
        return ResponseEntity.noContent().build();
    }
}
