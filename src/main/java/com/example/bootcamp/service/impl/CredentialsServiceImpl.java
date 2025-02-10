package com.example.bootcamp.service.impl;

import com.example.bootcamp.dto.CredentialsDTO;
import com.example.bootcamp.entity.Credentials;
import com.example.bootcamp.repository.CredentialsRepository;
import com.example.bootcamp.service.CredentialsService;
import com.example.bootcamp.util.CredentialsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CredentialsServiceImpl implements CredentialsService {

    private final CredentialsRepository credentialsRepository;

    @Override
    public List<CredentialsDTO> getAllCredentials() {
        return credentialsRepository.findAll().stream()
                .map(CredentialsMapper::convertDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CredentialsDTO getCredentialsById(Long id) {
        Optional<Credentials> credentials = credentialsRepository.findById(id);
        return credentials.map(CredentialsMapper::convertDTO).orElse(null);
    }

    @Override
    public CredentialsDTO createCredentials(CredentialsDTO dto) {
        Credentials credentials = new Credentials();
        credentials.setLogin(dto.getLogin());
        credentials.setHashedPassword(dto.getHashedPassword());

        Credentials savedCredentials = credentialsRepository.save(credentials);

        CredentialsDTO responseDto = new CredentialsDTO();
        responseDto.setLogin(savedCredentials.getLogin());
        responseDto.setHashedPassword(savedCredentials.getHashedPassword());

        return responseDto;
    }


    @Override
    public CredentialsDTO updateCredentials(Long id, CredentialsDTO dto) {
        Optional<Credentials> existingCredentialsOptional = credentialsRepository.findById(id);
        if (existingCredentialsOptional.isPresent()) {
            Credentials existingCredentials = existingCredentialsOptional.get();
            existingCredentials.setLogin(dto.getLogin());
            existingCredentials.setHashedPassword(dto.getHashedPassword());
            Credentials updatedCredentials = credentialsRepository.save(existingCredentials);
            return CredentialsMapper.convertDTO(updatedCredentials);
        }
        return null;
    }

    @Override
    public void deleteCredentials(Long id) {
        credentialsRepository.deleteById(id);
    }
}
