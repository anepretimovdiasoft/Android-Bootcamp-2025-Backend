package com.example.bootcamp.controller;

import com.example.bootcamp.dto.VolunteerDTO;
import com.example.bootcamp.dto.VolunteerRegisterDTO;
import com.example.bootcamp.service.VolunteerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        return ResponseEntity.status(HttpStatus.FOUND).body(volunteerService.getAllVolunteers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VolunteerDTO> getVolunteerById(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.FOUND).body(volunteerService.getVolunteerById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<VolunteerDTO> registerVolunteer(@RequestBody VolunteerRegisterDTO dto){
        volunteerService.createVolunteer(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<String> getByUsername(@PathVariable String username){
        VolunteerDTO volunteerDTO = volunteerService.getVolunteerByUsername(username);
        return ResponseEntity.status(HttpStatus.FOUND).body("Volunteer "+volunteerDTO.getUsername()+" is registered");
    }

    @GetMapping("/login")
    public ResponseEntity<VolunteerDTO> login(Authentication authentication){
        return ResponseEntity.status(HttpStatus.FOUND).body(volunteerService.getVolunteerByUsername(authentication.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VolunteerDTO> updateVolunteer(@PathVariable long id, @RequestBody VolunteerDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(volunteerService.updateVolunteer(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteVolunteer(@PathVariable long id){
        volunteerService.deleteVolunteer(id);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<VolunteerDTO>> getAllVolunteerPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.status(HttpStatus.FOUND).body(volunteerService.getAllVolunteerPaginated(pageable));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<VolunteerDTO> getVolunteerByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.FOUND).body(volunteerService.getVolunteerByName(name));
    }
}
