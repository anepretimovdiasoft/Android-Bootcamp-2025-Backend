package com.example.bootcamp.controller;

import com.example.bootcamp.dto.OrganizationDTO;
import com.example.bootcamp.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/organization")
public class OrganizationController {
    private final OrganizationService organizationService;

    @GetMapping
    List<OrganizationDTO> getAllOrganizations() {
        return organizationService.getAllOrganizations();
    }

    @GetMapping("/{id}")
    ResponseEntity<OrganizationDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(organizationService.getOrganizationById(id));
    }

    @PostMapping
    OrganizationDTO createUser(@RequestBody OrganizationDTO organizationDTO) {
        return organizationService.createOrganization(organizationDTO);
    }

    @PutMapping("/{id}")
    ResponseEntity<OrganizationDTO> updateUser(@PathVariable Long id, @RequestBody OrganizationDTO organizationDTO) {
        return ResponseEntity.ok(organizationService.updateOrganization(id, organizationDTO));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<OrganizationDTO> deleteUser(@PathVariable Long id) {
        organizationService.deleteOrganization(id);
        return ResponseEntity.noContent().build();
    }

}
