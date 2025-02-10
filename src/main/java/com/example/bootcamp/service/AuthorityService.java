package com.example.bootcamp.service;

import com.example.bootcamp.entity.Authority;

import java.util.List;

public interface AuthorityService {

    List<Authority> getAllAuthority();

    Authority add(Authority authority);
}
