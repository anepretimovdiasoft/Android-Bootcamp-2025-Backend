package com.example.bootcamp.util;

import com.example.bootcamp.dto.UsersDTO;
import com.example.bootcamp.entity.Users;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UsersMapper {
    public static UsersDTO convertDTO(Users user) {
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setId(user.getId());
        usersDTO.setUsername(user.getUsername());
        usersDTO.setCreated(user.getCreated());
        usersDTO.setUpdated(user.getUpdated());
        if (user.getCredentials() != null) {
            usersDTO.setCredentials(user.getCredentials());
        }

        if (user.getAuthority() != null) {
            usersDTO.setAuthority(user.getAuthority());
        }

        if (user.getProfile() != null) {
            usersDTO.setProfile(user.getProfile());
        }
        return usersDTO;

    }

    //TODO СРОЧНО ИСПРАВИТЬ К УТРУ ПЕРЕРАСПРЕДЕЛЕНИЕ ЭТО
}
