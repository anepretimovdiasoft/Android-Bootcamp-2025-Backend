package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.AuthorityDTO;
import com.example.bootcamp.entity.Authority;
import com.example.bootcamp.repository.AuthorityRepository;
import com.example.bootcamp.service.AuthorityService;
import com.example.bootcamp.util.AuthorityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository rolesRepository;

    @Override
    public List<AuthorityDTO> getAllAuthority() {
        return rolesRepository.findAll().stream()
                .map(AuthorityMapper::convertDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorityDTO getRoleById(Long id) {
        Optional<Authority> role = rolesRepository.findById(id);
        return role.map(AuthorityMapper::convertDTO).orElse(null);
    }

    @Override
    public AuthorityDTO createAuthority(AuthorityDTO dto) {
        Authority role = new Authority();
        role.setAuthority(dto.getName());
        Authority savedRole = rolesRepository.save(role);
        return AuthorityMapper.convertDTO(savedRole);
    }

    @Override
    public AuthorityDTO updateAuthority(Long id, AuthorityDTO dto) {
        Optional<Authority> existingRoleOptional = rolesRepository.findById(id);
        if (existingRoleOptional.isPresent()) {
            Authority existingRole = existingRoleOptional.get();
            existingRole.setAuthority(dto.getName());
            Authority updatedRole = rolesRepository.save(existingRole);
            return AuthorityMapper.convertDTO(updatedRole);
        }
        return null;
    }

    @Override
    public void deleteAuthority(Long id) {
        rolesRepository.deleteById(id);
    }
}
