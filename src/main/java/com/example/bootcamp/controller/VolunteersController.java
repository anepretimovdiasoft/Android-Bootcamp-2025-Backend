package com.example.bootcamp.controller;

import com.example.bootcamp.service.VolunteerService;
import com.example.bootcamp.dto.entity.volunteer.VolunteerDTO;
import com.example.bootcamp.dto.entity.volunteer.VolunteerRegisterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/volunteers")
public class VolunteersController {
    private final VolunteerService volunteerService;

    @GetMapping
    public ResponseEntity<List<VolunteerDTO>> getAllVolunteers() {
        return ResponseEntity.ok(volunteerService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VolunteerDTO> getVolunteerById(@PathVariable long id) {
        return ResponseEntity.ok(volunteerService.getById(id));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<VolunteerDTO> getVolunteerByEmail(@PathVariable String email) {
        return ResponseEntity.ok(volunteerService.getByEmail(email));
    }

    @GetMapping("/telephone/{telephone}")
    public ResponseEntity<VolunteerDTO> getVolunteerByTelephone(@PathVariable String telephone) {
        return ResponseEntity.ok(volunteerService.getByTelephone(telephone));
    }

    @GetMapping("/free")
    public ResponseEntity<List<VolunteerDTO>> getFreeVolunteers() {
        return ResponseEntity.ok(volunteerService.free());
    }

    @PostMapping
    public ResponseEntity<VolunteerDTO> createVolunteer(@RequestBody VolunteerRegisterDTO volunteerRegisterDTO) {
        return ResponseEntity.ok(volunteerService.create(volunteerRegisterDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VolunteerDTO> updateVolunteer(@PathVariable long id, @RequestBody VolunteerDTO volunteerDTO) {
        return ResponseEntity.ok(volunteerService.update(id, volunteerDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVolunteer(@PathVariable long id) {
        volunteerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
