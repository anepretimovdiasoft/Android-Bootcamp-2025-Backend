package com.example.bootcamp.service;

import com.example.bootcamp.entity.Authority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorityService {
    Authority add(Authority authority);
    List<Authority> getAll();

    String isAdmin(Long id);
}
