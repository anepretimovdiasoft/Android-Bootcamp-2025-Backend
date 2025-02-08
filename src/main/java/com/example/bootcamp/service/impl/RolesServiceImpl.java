package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.RolesDTO;
import com.example.bootcamp.entity.Roles;
import com.example.bootcamp.repository.RolesRepository;
import com.example.bootcamp.service.RolesService;
import com.example.bootcamp.util.RolesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RolesServiceImpl implements RolesService {

    private final RolesRepository rolesRepository;

    @Override
    public List<RolesDTO> getAllRoles() {
        return rolesRepository.findAll().stream()
                .map(RolesMapper::convertDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RolesDTO getRoleById(Long id) {
        Optional<Roles> role = rolesRepository.findById(id);
        return role.map(RolesMapper::convertDTO).orElse(null);
    }

    @Override
    public RolesDTO createRole(RolesDTO dto) {
        Roles role = new Roles();
        role.setRole(dto.getName());
        Roles savedRole = rolesRepository.save(role);
        return RolesMapper.convertDTO(savedRole);
    }

    @Override
    public RolesDTO updateRole(Long id, RolesDTO dto) {
        Optional<Roles> existingRoleOptional = rolesRepository.findById(id);
        if (existingRoleOptional.isPresent()) {
            Roles existingRole = existingRoleOptional.get();
            existingRole.setRole(dto.getName());
            Roles updatedRole = rolesRepository.save(existingRole);
            return RolesMapper.convertDTO(updatedRole);
        }
        return null;
    }

    @Override
    public void deleteRole(Long id) {
        rolesRepository.deleteById(id);
    }
}
