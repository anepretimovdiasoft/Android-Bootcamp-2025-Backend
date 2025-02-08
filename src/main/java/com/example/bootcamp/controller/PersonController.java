package com.example.bootcamp.controller;

import com.example.bootcamp.dto.PersonDTO;
import com.example.bootcamp.dto.PersonRegisterDTO;
import com.example.bootcamp.entity.Authority;
import com.example.bootcamp.entity.Person;
import com.example.bootcamp.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class PersonController {
    private final PersonService personService;

    @GetMapping
    public List<PersonDTO> getAllPerson() {
        return personService.getAllPerson();
    }

    @GetMapping("/info/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable long id) {
        return ResponseEntity.ok(personService.getPersonById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonRegisterDTO personRegisterDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.createPerson(personRegisterDTO));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<String> getByUsername(@PathVariable String username) {
        PersonDTO personDTO = personService.getPersonByUsername(username);
        return ResponseEntity.ok("User " + personDTO.getUsername() + " is registered");
    }

    @GetMapping("/login")
    public ResponseEntity<PersonDTO> login(Authentication authentication){
        return ResponseEntity.ok(personService.getPersonByUsername(authentication.getName()));
    }

    @GetMapping("/one/{volunteerId}")
    public List<PersonDTO> getAllPersonAtVolunteerCenter(@PathVariable long volunteerId){
        return personService.getAllPersonAtCenter(volunteerId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable long id, @RequestBody PersonDTO personDTO) {
        return ResponseEntity.ok(personService.updatePerson(id, personDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable long id){
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<PersonDTO>> getAllPersonPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(personService.getAllPersonPaginated(pageable));
    }

    @PutMapping("volunteer/add/{id}/{name}")
    public ResponseEntity<PersonDTO> registerAtVolunteerCentre(@PathVariable long id, @PathVariable String name){
        return ResponseEntity.ok(personService.registerAtVolunteerCenter(id, name));
    }
}
