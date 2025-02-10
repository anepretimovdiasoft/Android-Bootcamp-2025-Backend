package com.example.bootcamp.controller;

import com.example.bootcamp.dto.CredentialsDTO;
import com.example.bootcamp.service.CredentialsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/credentials")
@RequiredArgsConstructor
public class CredentialsController {

    private final CredentialsService credentialsService;

    @GetMapping
    public List<CredentialsDTO> getAllCredentials() {
        return credentialsService.getAllCredentials();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CredentialsDTO> getCredentialsById(@PathVariable long id) {
        return ResponseEntity.ok(credentialsService.getCredentialsById(id));
    }

    @PostMapping
    public ResponseEntity<CredentialsDTO> createCredentials(@RequestBody CredentialsDTO dto) {
        return ResponseEntity.ok(credentialsService.createCredentials(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CredentialsDTO> updateCredentials(@PathVariable long id, @RequestBody CredentialsDTO dto) {
        return ResponseEntity.ok(credentialsService.updateCredentials(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCredentials(@PathVariable long id) {
        credentialsService.deleteCredentials(id);
        return ResponseEntity.noContent().build();
    }
}
