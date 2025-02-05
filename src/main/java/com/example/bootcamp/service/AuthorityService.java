package com.example.bootcamp.service;

import com.example.bootcamp.entity.Authority;

import java.util.List;

public interface AuthorityService {
    Authority add(Authority authority);

    List<Authority> getAll();
}
