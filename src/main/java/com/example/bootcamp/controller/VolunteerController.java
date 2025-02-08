package com.example.bootcamp.controller;

import com.example.bootcamp.dto.VolunteerDTO;
import com.example.bootcamp.dto.VolunteerRegisterDTO;
import com.example.bootcamp.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/volunteer")
@RequiredArgsConstructor
public class VolunteerController {
    private final VolunteerService volunteerService;

    @GetMapping
    public ResponseEntity<List<VolunteerDTO>> getAllVolunteer(){
        return ResponseEntity.status(200).body(volunteerService.getAllVolunteers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VolunteerDTO> getVolunteerById(@PathVariable long id){
        return ResponseEntity.status(200).body(volunteerService.getVolunteerById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<VolunteerDTO> registerVolunteer(@RequestBody VolunteerRegisterDTO dto){
        volunteerService.createVolunteer(dto);
        return ResponseEntity.status(200).body(null);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<String> getByUsername(@PathVariable String username){
        VolunteerDTO volunteerDTO = volunteerService.getVolunteerByUserName(username);
        return ResponseEntity.status(HttpStatus.OK).body("Volunteer "+volunteerDTO.getUsername()+" is registered");
    }

    @GetMapping("/login")
    public ResponseEntity<VolunteerDTO> login(Authentication authentication){
        return ResponseEntity.status(HttpStatus.OK).body(volunteerService.getVolunteerByUserName(authentication.getName()));
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
