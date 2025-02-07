package com.example.edu.controller;

import com.example.edu.dto.PersonDTO;
import com.example.edu.dto.PersonRegisterDto;
import com.example.edu.service.PersonService;
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
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @GetMapping
    public List<PersonDTO> getAllPersons() {
        return personService.getAllPersons();
    }

    @PostMapping("/register")
    public ResponseEntity<PersonDTO> register(@RequestBody PersonRegisterDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.createPerson(dto));
    }

    @GetMapping("/login")
    public ResponseEntity<PersonDTO> login(Authentication authentication) {
        return ResponseEntity.ok(personService.getPersonByUsername(authentication.getName()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.getPersonById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable Long id, @RequestBody PersonDTO dto) {
        return ResponseEntity.ok(personService.updatePerson(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<String> getByUsername(@PathVariable String username) {
        PersonDTO personDTO = personService.getPersonByUsername(username);
        return ResponseEntity.ok("User " + personDTO.getUsername() + " is registered");
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<PersonDTO>> getAllPersonsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(personService.getAllPersonsPaginated(pageable));
    }
}
