package com.example.bootcamp.util;

import com.example.bootcamp.dto.AuthorityDTO;
import com.example.bootcamp.entity.Authority;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthorityMapper {
    public static AuthorityDTO convertDTO(Authority role) {
        AuthorityDTO rolesDTO = new AuthorityDTO();
        rolesDTO.setId(role.getId());
        rolesDTO.setName(role.getAuthority());
        return rolesDTO;
    }
}
