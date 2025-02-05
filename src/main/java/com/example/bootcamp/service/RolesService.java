package com.example.bootcamp.service;

import com.example.bootcamp.dto.RolesDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RolesService {
    List<RolesDTO> getAllRoles();
    RolesDTO getRoleById(Long id);
    RolesDTO createRole(RolesDTO dto);
    RolesDTO updateRole(Long id, RolesDTO dto);
    void deleteRole(Long id);
}
