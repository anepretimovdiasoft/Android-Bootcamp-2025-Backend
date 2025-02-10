package com.example.edu.service;

import com.example.edu.dto.authorities.AuthorityCreateDTO;
import com.example.edu.dto.authorities.AuthorityDTO;

import java.util.List;

public interface AuthorityService {
    AuthorityDTO add(AuthorityCreateDTO authority);

    AuthorityDTO getByAuthority(String authority);

    List<AuthorityDTO> getAll();
}
