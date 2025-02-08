package com.example.edu.service.impl;

import com.example.edu.dto.authorities.AuthorityCreateDTO;
import com.example.edu.dto.authorities.AuthorityDTO;
import com.example.edu.entity.Authority;
import com.example.edu.exception.NotFoundException;
import com.example.edu.repository.AuthorityRepository;
import com.example.edu.service.AuthorityService;
import com.example.edu.utils.mappers.AuthorityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {
    private final AuthorityRepository authorityRepository;

    @Override
    public AuthorityDTO add(AuthorityCreateDTO dto) {
        Optional<Authority> existingAuthority = authorityRepository.findByAuthority(dto.getAuthority());
        if (existingAuthority.isPresent()) return AuthorityMapper.convertToDTO(existingAuthority.get());

        Authority authority = new Authority();
        authority.setAuthority(dto.getAuthority());
        authorityRepository.save(authority);

        return AuthorityMapper.convertToDTO(authority);
    }

    @Override
    public AuthorityDTO getByAuthority(String authority) {
        Optional<Authority> optionalAuthority = authorityRepository.findByAuthority(authority);
        if (optionalAuthority.isEmpty()) throw new NotFoundException("Authority was not found");
        return AuthorityMapper.convertToDTO(optionalAuthority.get());
    }

    @Override
    public List<AuthorityDTO> getAll() {
        return authorityRepository.findAll()
                .stream()
                .map(AuthorityMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
