package com.example.bootcamp.controller;

import com.example.bootcamp.dto.OrganizationDTO;
import com.example.bootcamp.dto.OrganizationRegisterDTO;
import com.example.bootcamp.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/1.0/organization")
public class OrganizationController {
    public final OrganizationService organizationService;

    @GetMapping("/organization/{id}")
    public ResponseEntity<OrganizationDTO> getOrganizationById(@PathVariable Long id){
        return ResponseEntity.ok(organizationService.getOrganizationById(id));
    }

    @GetMapping("/organizations")
    public List<OrganizationDTO> getAllOrganizations(){
        return organizationService.getAllOrganization();
    }

    @GetMapping("/{name}")
    public ResponseEntity<OrganizationDTO> getOrganizationByName(@PathVariable String name){
        return ResponseEntity.ok(organizationService.getOrganizationByName(name));
    }

    @PostMapping("/new")
    public OrganizationDTO createOrganization(@RequestBody OrganizationRegisterDTO organizationRegisterDTO){
        return organizationService.createOrganization(organizationRegisterDTO);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<OrganizationDTO> updateOrganization(@PathVariable Long id, @RequestBody OrganizationDTO organizationDTO){
        return ResponseEntity.ok(organizationService.updateOrganization(id, organizationDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable Long id){
        organizationService.deleteOrganization(id);
        return ResponseEntity.noContent().build();
    }
}
