package com.example.edu.service.impl;

import com.example.edu.entity.Authority;
import com.example.edu.repository.AuthorityRepository;
import com.example.edu.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Override
    public Authority add(Authority authority) {

        Optional<Authority> optionalAuthority = authorityRepository.findByAuthority(authority.getAuthority());

        return optionalAuthority.orElseGet(() -> authorityRepository.save(authority));
    }

    @Override
    public List<Authority> getAll() {
        return authorityRepository.findAll();
    }
}
