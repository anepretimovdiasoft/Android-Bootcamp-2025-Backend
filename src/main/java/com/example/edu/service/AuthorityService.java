package com.example.edu.service;

import com.example.edu.entity.Authority;

import java.util.List;

public interface AuthorityService {
    Authority add(Authority authority);

    List<Authority> getAll();
}
