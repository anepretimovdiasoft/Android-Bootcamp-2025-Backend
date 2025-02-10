package com.example.bootcamp.service;

import com.example.bootcamp.dto.CredentialsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CredentialsService {
    List<CredentialsDTO> getAllCredentials();
    CredentialsDTO getCredentialsById(Long id);
    CredentialsDTO createCredentials(CredentialsDTO dto);
    CredentialsDTO updateCredentials(Long id, CredentialsDTO dto);
    void deleteCredentials(Long id);
}
