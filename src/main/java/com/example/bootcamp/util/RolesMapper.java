package com.example.bootcamp.util;

import com.example.bootcamp.dto.RolesDTO;
import com.example.bootcamp.entity.Roles;
import lombok.experimental.UtilityClass;

@UtilityClass
public class RolesMapper {
    public static RolesDTO convertDTO(Roles role) {
        RolesDTO rolesDTO = new RolesDTO();
        rolesDTO.setId(role.getId());
        return rolesDTO;
    }
}
