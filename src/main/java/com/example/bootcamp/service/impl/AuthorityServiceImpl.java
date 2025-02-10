package com.example.bootcamp.service.impl;

import com.example.bootcamp.entity.Authority;
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
    public Authority add(Authority authority) {

        Optional<Authority> optionalAuthority = authorityRepository.findByAuthority(authority.getAuthority());

        //return optionalAuthority.orElseGet(() -> authorityRepository.save(authority));

        if (optionalAuthority.isPresent()) return optionalAuthority.get();
        else return authorityRepository.save(authority);
    }
    @Override
    public List<Authority> getAll() {
        return authorityRepository.findAll();
    }

    @Override
    public String isAdmin(Long id) {
        Optional<Authority> optionalAuthority = authorityRepository.findById(id);
        Authority existingsAuthority = optionalAuthority.get();

        return existingsAuthority.getAuthority();
    }
}
