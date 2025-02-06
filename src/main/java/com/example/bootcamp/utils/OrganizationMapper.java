package com.example.bootcamp.utils;

import com.example.bootcamp.dto.OrganizationDTO;
import com.example.bootcamp.entity.Organization;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrganizationMapper {
    public OrganizationDTO convertToOrganizationDTO(Organization organization){
        OrganizationDTO organizationDTO = new OrganizationDTO();

        organizationDTO.setId(organization.getId());
        organizationDTO.setLon(organization.getLon());
        organizationDTO.setLat(organization.getLat());
        organizationDTO.setAddress(organization.getAddress());
        organizationDTO.setInfo(organization.getInfo());
        organizationDTO.setName(organization.getName());
        organizationDTO.setImagePhoto(organization.getImagePhoto());

        return organizationDTO;
    }
}
