package com.example.bootcamp.controller;

import com.example.bootcamp.dto.AuthorityDTO;
import com.example.bootcamp.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authority")
@RequiredArgsConstructor
public class AuthorityController {

    private final AuthorityService rolesService;

    @GetMapping
    public List<AuthorityDTO> getAllAuthority() {
        return rolesService.getAllAuthority();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorityDTO> getAuthorityById(@PathVariable long id) {
        return ResponseEntity.ok(rolesService.getRoleById(id));
    }

    @PostMapping
    public ResponseEntity<AuthorityDTO> createRole(@RequestBody AuthorityDTO dto) {
        return ResponseEntity.ok(rolesService.createAuthority(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorityDTO> updateRole(@PathVariable long id, @RequestBody AuthorityDTO dto) {
        return ResponseEntity.ok(rolesService.updateAuthority(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable long id) {
        rolesService.deleteAuthority(id);
        return ResponseEntity.noContent().build();
    }
}
