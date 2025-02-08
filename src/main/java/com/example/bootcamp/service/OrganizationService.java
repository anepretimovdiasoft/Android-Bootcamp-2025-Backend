package com.example.bootcamp.service;

import com.example.bootcamp.dto.OrganizationDTO;
import com.example.bootcamp.dto.OrganizationRegisterDTO;
import com.example.bootcamp.entity.Organization;

import java.util.List;

public interface OrganizationService{
    List<OrganizationDTO> getAllOrganization();

    OrganizationDTO getOrganizationById(Long id);

    OrganizationDTO getOrganizationByName(String name);

    OrganizationDTO createOrganization(OrganizationRegisterDTO organizationRegisterDTO);

    OrganizationDTO updateOrganization(Long id, OrganizationDTO organizationDTO);

    void deleteOrganization(Long id);
}
