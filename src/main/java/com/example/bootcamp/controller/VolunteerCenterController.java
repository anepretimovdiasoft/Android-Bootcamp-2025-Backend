package com.example.bootcamp.controller;

import com.example.bootcamp.dto.VolunteerCenterDTO;
import com.example.bootcamp.service.VolunteerCenterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/volunteercenter")
@RequiredArgsConstructor
public class VolunteerCenterController {

    private final VolunteerCenterService volunteerCenterService;

    @GetMapping
    public ResponseEntity<List<VolunteerCenterDTO>> getAllVolunteerCenter(){
        return ResponseEntity.status(HttpStatus.FOUND).body(volunteerCenterService.getAllVolunteerCenter());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VolunteerCenterDTO> getVolunteerCenterById(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(volunteerCenterService.getVolunteerCenterById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<VolunteerCenterDTO> createVolunteerCenter(@RequestBody VolunteerCenterDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(volunteerCenterService.createVolunteerCenter(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VolunteerCenterDTO> updateVolunteerCenter(@PathVariable long id, @RequestBody VolunteerCenterDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(volunteerCenterService.updateVolunteerCenter(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVolunteerCenter(@PathVariable Long id){
        volunteerCenterService.deleteVolunteerCenter(id);
    }
}
