package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.OrganizationDTO;
import com.example.bootcamp.dto.OrganizationRegisterDTO;
import com.example.bootcamp.entity.Organization;
import com.example.bootcamp.exception.OrganizationAlreadyExistsException;
import com.example.bootcamp.exception.OrganizationNotFoundException;
import com.example.bootcamp.repository.OrganizationRepository;
import com.example.bootcamp.service.OrganizationService;
import com.example.bootcamp.utils.OrganizationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;

    @Override
    public List<OrganizationDTO> getAllOrganization() {
        return organizationRepository.findAll().stream()
                .map(OrganizationMapper::convertToOrganizationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrganizationDTO getOrganizationById(Long id) {
        return organizationRepository.findById(id)
                .map(OrganizationMapper::convertToOrganizationDTO)
                .orElseThrow(() -> new OrganizationNotFoundException("Organization not found!"));
    }

    @Override
    public OrganizationDTO getOrganizationByName(String name) {
        Optional<Organization> organization = organizationRepository.findByName(name);

        if(organization.isEmpty()){
            throw new OrganizationNotFoundException("Organization with " + name + " not found!");
        }

        return OrganizationMapper.convertToOrganizationDTO(organization.get());
    }

    @Override
    public OrganizationDTO createOrganization(OrganizationRegisterDTO organizationRegisterDTO) {

        if(organizationRepository.findByName(organizationRegisterDTO.getName()).isPresent()){
            throw new OrganizationAlreadyExistsException("Organization already exists!");
        }

        Organization organization = new Organization();

        organization.setAddress(organizationRegisterDTO.getAddress());
        organization.setName(organizationRegisterDTO.getName());
        organization.setLat(organizationRegisterDTO.getLat());
        organization.setLon(organizationRegisterDTO.getLon());
        organization.setInfo(organizationRegisterDTO.getInfo());
        organization.setImagePhoto(organizationRegisterDTO.getImagePhoto());

        return OrganizationMapper.convertToOrganizationDTO(organizationRepository.save(organization));
    }

    @Override
    public OrganizationDTO updateOrganization(Long id, OrganizationDTO organizationDTO) {
        Organization organization = organizationRepository.findById(id).orElseThrow(() -> new OrganizationNotFoundException("Organization not found!"));

        organization.setImagePhoto(organizationDTO.getImagePhoto());
        organization.setName(organizationDTO.getName());
        organization.setLat(organizationDTO.getLat());
        organization.setLon(organizationDTO.getLon());
        organization.setInfo(organizationDTO.getInfo());
        organization.setAddress(organizationDTO.getAddress());

        return OrganizationMapper.convertToOrganizationDTO(organizationRepository.save(organization));
    }

    @Override
    public void deleteOrganization(Long id) {
        organizationRepository.deleteById(id);
    }
}
