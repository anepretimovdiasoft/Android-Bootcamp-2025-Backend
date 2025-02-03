package com.example.bootcamp.controller;

import com.example.bootcamp.dto.RolesDTO;
import com.example.bootcamp.service.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RolesController {

    private final RolesService rolesService;

    @GetMapping
    public List<RolesDTO> getAllRoles() {
        return rolesService.getAllRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolesDTO> getRoleById(@PathVariable long id) {
        return ResponseEntity.ok(rolesService.getRoleById(id));
    }

    @PostMapping
    public ResponseEntity<RolesDTO> createRole(@RequestBody RolesDTO dto) {
        return ResponseEntity.ok(rolesService.createRole(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolesDTO> updateRole(@PathVariable long id, @RequestBody RolesDTO dto) {
        return ResponseEntity.ok(rolesService.updateRole(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable long id) {
        rolesService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }
}
