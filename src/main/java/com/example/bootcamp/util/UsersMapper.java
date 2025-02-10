package com.example.bootcamp.util;

import com.example.bootcamp.dto.UsersDTO;
import com.example.bootcamp.entity.Users;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UsersMapper {
    public static UsersDTO convertDTO(Users user) {
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setUsername(user.getUsername());

        if (user.getAuthority() != null) {
            usersDTO.setAuthorityId(user.getAuthority().getId());
        }

        if (user.getProfile() != null) {
            usersDTO.setProfileId((user.getProfile().getId()));
        }
        return usersDTO;

    }

}
