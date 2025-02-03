package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.OrganizationDTO;
import com.example.bootcamp.entity.Organization;
import com.example.bootcamp.exception.OrganizationNotFoundException;
import com.example.bootcamp.repository.OrganizationRepository;
import com.example.bootcamp.service.OrganizationService;
import com.example.bootcamp.utils.OrganizationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;

    @Override
    public List<OrganizationDTO> getAllOrganizations() {
        return organizationRepository.findAll().stream()
                .map(OrganizationMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrganizationDTO getOrganizationById(Long id) {
        return organizationRepository.findById(id)
                .map(OrganizationMapper::convertToDTO)
                .orElseThrow(() -> new OrganizationNotFoundException("Organization with id " + id + " not found"));
    }

    @Override
    public OrganizationDTO createOrganization(OrganizationDTO dto) {
        Organization org = new Organization();

        org.setName(dto.getName());
        org.setAddress(dto.getAddress());
        org.setLatitude(dto.getLatitude());
        org.setLongitude(dto.getLongitude());

        return OrganizationMapper.convertToDTO(organizationRepository.save(org));
    }

    @Override
    public OrganizationDTO updateOrganization(Long id, OrganizationDTO dto) {
        Organization org = organizationRepository.findById(id).orElseThrow(() -> new OrganizationNotFoundException("Organization with id " + id + " not found"));

        org.setName(dto.getName());
        org.setAddress(dto.getAddress());
        org.setLatitude(dto.getLatitude());
        org.setLongitude(dto.getLongitude());

        return OrganizationMapper.convertToDTO(organizationRepository.save(org));
    }

    @Override
    public void deleteOrganization(Long id) {
        organizationRepository.deleteById(id);
    }
}
