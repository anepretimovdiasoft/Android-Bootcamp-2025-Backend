package com.example.bootcamp.controller;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.dto.UserRegisterDTO;
import com.example.bootcamp.entity.User;
import com.example.bootcamp.entity.Volunteer_centers;
import com.example.bootcamp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
@RequiredArgsConstructor
public class UserController {

    private final UserService personService;


    @GetMapping
    public List<UserDTO> getAllPersons() {
        return personService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getPersonById(@PathVariable Long id) {
        return ResponseEntity.ok(personService.getUserById(id));
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> createPerson(@RequestBody UserRegisterDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED).body(personService.createUser(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updatePerson(@PathVariable Long id, @RequestBody UserDTO dto) {
        return ResponseEntity.ok(personService.updateUser(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        personService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<String> getByUsername(@PathVariable String username) {
        UserDTO userDTO = personService.getUserByUsername(username);
        return ResponseEntity.ok("User " + userDTO.getUsername() + " is registered");
    }

    @GetMapping("/login")
    public ResponseEntity<UserDTO> login(Authentication authentication) {
        return ResponseEntity.ok(personService.getUserByUsername(authentication.getName()));
    }

    @GetMapping("/active")
    public List<UserDTO> getActiveUsers() {
        return personService.getActiveUsers();
    }


    @GetMapping("/inactive")
    public List<UserDTO> getInactiveUsers() {
        return personService.getInactiveUsers();
    }

    @GetMapping("/active/{center_id}")
    public List<UserDTO> getActiveVolunteers(@PathVariable Long center_id) {
        return personService.findVolunteersByCenter(center_id);
    }

    @PostMapping("/volunteers/{volunteer_id}/centers/{center_id}")
    public ResponseEntity<String> attachVolunteer(@PathVariable Long volunteer_id, @PathVariable Long center_id) {
        personService.attachVolunteerToCenter(volunteer_id, center_id);
        return ResponseEntity.ok("Волонтёр прикреплён к центру.");
    }


    @DeleteMapping("/volunteers/{volunteer_id}/centers")
    public ResponseEntity<String> detachVolunteer(@PathVariable Long volunteer_id) {
        personService.detachVolunteerFromCenter(volunteer_id);
        return ResponseEntity.ok("Волонтёр откреплён от центра.");
    }




}








