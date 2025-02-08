package com.example.bootcamp.service;

import com.example.bootcamp.dto.AuthorityDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorityService {
    List<AuthorityDTO> getAllAuthority();
    AuthorityDTO getRoleById(Long id);
    AuthorityDTO createAuthority(AuthorityDTO dto);
    AuthorityDTO updateAuthority(Long id, AuthorityDTO dto);
    void deleteAuthority(Long id);
}
