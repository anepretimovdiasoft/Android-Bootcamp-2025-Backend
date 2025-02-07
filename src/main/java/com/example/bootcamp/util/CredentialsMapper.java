package com.example.bootcamp.util;

import com.example.bootcamp.dto.CredentialsDTO;
import com.example.bootcamp.entity.Credentials;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CredentialsMapper {
    public static CredentialsDTO convertDTO(Credentials credentials) {
        CredentialsDTO credentialsDTO = new CredentialsDTO();
        credentialsDTO.setId(credentials.getId());
        credentialsDTO.setLogin(credentials.getLogin());
        credentialsDTO.setHashedPassword(credentials.getHashedPassword());
        return credentialsDTO;
    }
}
