package com.example.bootcamp.controller;

import com.example.bootcamp.dto.PersonDTO;
import com.example.bootcamp.dto.VolunteerCentreDTO;
import com.example.bootcamp.entity.VolunteerCentre;
import com.example.bootcamp.service.PersonService;
import com.example.bootcamp.service.VolunteerCentreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/volunteer")
public class VolunteerCentreController {

    public final VolunteerCentreService volunteerCentreService;
    public final PersonService personService;

    @GetMapping
    public List<VolunteerCentreDTO> getAllVolunteerCentre(){
        return volunteerCentreService.getAllVolunteerCentre();
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<VolunteerCentreDTO> getVolunteerCentreById(@PathVariable long id){
        return ResponseEntity.ok(volunteerCentreService.getVolunteerCentreById(id));
    }

    @GetMapping("/one/{volunteerId}")
    public List<PersonDTO> getAllPersonAtVolunteerCenter(@PathVariable long volunteerId){
        return personService.getAllPersonAtCenter(volunteerId);
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<VolunteerCentreDTO>> getAllVolunteerCenterPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(volunteerCentreService.getVolunteerCentrePaginated(pageable));
    }

    @PostMapping("/register")
    public ResponseEntity<VolunteerCentreDTO> createVolunteerCentre(@RequestBody VolunteerCentreDTO volunteerCentre) {
        return ResponseEntity.ok(volunteerCentreService.createVolunteerCentre(volunteerCentre));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<VolunteerCentreDTO> updateVolunteerCentre(@RequestBody VolunteerCentreDTO dto, @PathVariable long id) {
        return ResponseEntity.ok(volunteerCentreService.updateVolunteerCentre(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteVolunteerCentre(@PathVariable long id){
        volunteerCentreService.deleteVolunteerCentre(id);
    }
}
