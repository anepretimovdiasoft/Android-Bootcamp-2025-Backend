package com.example.edu.utils.mappers;

import com.example.edu.dto.authorities.AuthorityDTO;
import com.example.edu.entity.Authority;
import com.example.edu.utils.GlobalUtils;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.List;

@UtilityClass
public class AuthorityMapper {
    public AuthorityDTO convertToDTO(Authority authority) {
        AuthorityDTO dto = new AuthorityDTO();
        dto.setId(authority.getId());
        dto.setAuthority(authority.getAuthority());
        return dto;
    }

    public List<AuthorityDTO> convertAllToDTO(Collection<Authority> authorities) {
        return GlobalUtils.convertAllToDTO(authorities, AuthorityMapper::convertToDTO);
    }
}
