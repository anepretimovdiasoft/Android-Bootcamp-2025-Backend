package com.example.bootcamp.service;

import com.example.bootcamp.dto.OrganizationDTO;

import java.util.List;


public interface OrganizationService {
    List<OrganizationDTO> getAllOrganizations();

    OrganizationDTO getOrganizationById(Long id);

    OrganizationDTO createOrganization(OrganizationDTO dto);

    OrganizationDTO updateOrganization(Long id, OrganizationDTO dto);

    void deleteOrganization(Long id);
}
