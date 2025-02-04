package com.example.bootcamp.controller;

import com.example.bootcamp.dto.VolunteerDTO;
import com.example.bootcamp.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/volunteer")
@RequiredArgsConstructor
public class VolunteerController {
    private final VolunteerService volunteerService;

    @GetMapping
    public ResponseEntity<List<VolunteerDTO>> getAllVolunteerCenter(){
        return ResponseEntity.status(200).body(volunteerService.getAllVolunteers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VolunteerDTO> getVolunteerCenterById(@PathVariable long id){
        return ResponseEntity.status(200).body(volunteerService.getVolunteerById(id));
    }

    @PostMapping
    public ResponseEntity<VolunteerDTO> createVolunteer(@RequestBody VolunteerDTO dto){
        volunteerService.createVolunteer(dto);
        return ResponseEntity.status(200).body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VolunteerDTO> updateVolunteer(@PathVariable long id, @RequestBody VolunteerDTO dto){
        return ResponseEntity.status(200).body(volunteerService.updateVolunteer(id, dto));
    }

    @DeleteMapping("/{id}")
    public void deleteVolunteer(@PathVariable long id){
        volunteerService.deleteVolunteer(id);
    }
}
