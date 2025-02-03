package com.example.bootcamp.utils;

import com.example.bootcamp.dto.OrganizationDTO;
import com.example.bootcamp.entity.Organization;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrganizationMapper {
    public static OrganizationDTO convertToDTO(Organization organization) {
        OrganizationDTO organizationDTO = new OrganizationDTO();

        organizationDTO.setId(organization.getId());
        organizationDTO.setName(organization.getName());
        organizationDTO.setAddress(organization.getAddress());
        organizationDTO.setLatitude(organization.getLatitude());
        organizationDTO.setLongitude(organization.getLongitude());

        return organizationDTO;
    }
}
