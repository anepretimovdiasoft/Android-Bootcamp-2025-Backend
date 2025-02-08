package com.example.bootcamp.util;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {
    public UserDTO convertToDto(User user){
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setUsername(user.getUsername());
        dto.setContactInfo(user.getContactInfo());
        dto.setBiography(user.getBiography());
        dto.setPhoto(user.getPhoto());
        if (user.getStatus() != null) {
            dto.setStatus(user.getStatus().getId());
        } else {
            dto.setStatus(null);
        }
        return dto;
    }
}
