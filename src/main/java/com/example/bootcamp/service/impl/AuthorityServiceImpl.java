package com.example.bootcamp.service.impl;

import com.example.bootcamp.entity.Authority;
import com.example.bootcamp.exceptions.AuthorityAlreadyExistsException;
import com.example.bootcamp.repository.AuthorityRepository;
import com.example.bootcamp.service.AuthorityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    @Override
    public List<Authority> getAllAuthority() {
        return authorityRepository.findAll();
    }

    @Override
    public Authority add(Authority authority) {
        Optional<Authority> optionalAuthority = authorityRepository.findByAuthority(authority.getAuthority());
        if(optionalAuthority.isPresent()) throw new AuthorityAlreadyExistsException("Authority "+authority.getAuthority()+" already exists");
        return authorityRepository.save(authority);
    }
}
