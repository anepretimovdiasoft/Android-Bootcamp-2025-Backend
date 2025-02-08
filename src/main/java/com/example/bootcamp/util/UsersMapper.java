package com.example.bootcamp.util;

import com.example.bootcamp.dto.UsersDTO;
import com.example.bootcamp.entity.Users;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UsersMapper {
    public static UsersDTO convertDTO(Users user) {
        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setId(user.getId());
        return usersDTO;

    }
}
