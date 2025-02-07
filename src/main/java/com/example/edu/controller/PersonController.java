package com.example.edu.controller;

import com.example.edu.dto.person.PersonDTO;
import com.example.edu.dto.person.PersonRegisterDto;
import com.example.edu.dto.person.PersonUpdateDTO;
import com.example.edu.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class PersonController {
    private final PersonService personService;

    @PostMapping("/register")  // Public
    public ResponseEntity<PersonDTO> register(@RequestBody PersonRegisterDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.createPerson(dto));
    }

    @GetMapping("/login")  // Public
    public ResponseEntity<PersonDTO> login(Authentication authentication) {
        return ResponseEntity.ok(personService.getPersonByUsername(authentication.getName()));
    }

    @GetMapping("/username/{username}")  // Public
    public ResponseEntity<String> getByUsername(@PathVariable String username) {
        PersonDTO personDTO = personService.getPersonByUsername(username);
        return ResponseEntity.ok("User " + personDTO.getUsername() + " is registered");
    }

    @GetMapping("/me")  // Auth only
    public ResponseEntity<PersonDTO> getMe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return ResponseEntity.ok(personService.getPersonByUsername(username));
    }

    @PutMapping("/me")  // Auth only
    public ResponseEntity<PersonDTO> updateMe(@RequestBody PersonUpdateDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return ResponseEntity.ok(personService.updatePerson(username, dto));
    }

    @PatchMapping("/me")  // Auth only
    public ResponseEntity<PersonDTO> patchMe(@RequestBody PersonUpdateDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return ResponseEntity.ok(personService.patchPerson(username, dto));
    }

    @GetMapping("/{id}")  // Admin only
    public ResponseEntity<PersonDTO> getPersonById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.getPersonById(id));
    }

    @PutMapping("/{id}")  // Admin only
    public ResponseEntity<PersonDTO> updatePersonById(@PathVariable Long id, @RequestBody PersonUpdateDTO dto) {
        return ResponseEntity.ok(personService.updatePerson(id, dto));
    }

    @PatchMapping("/{id}")  // Admin only
    public ResponseEntity<PersonDTO> patchPersonById(@PathVariable Long id, @RequestBody PersonUpdateDTO dto) {
        return ResponseEntity.ok(personService.patchPerson(id, dto));
    }

    @DeleteMapping("/{id}")  // Admin only
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("")  // Admin only
    public List<PersonDTO> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/paginated")  // Admin only
    public ResponseEntity<Page<PersonDTO>> getAllPersonsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(personService.getAllPersonsPaginated(pageable));
    }
}
