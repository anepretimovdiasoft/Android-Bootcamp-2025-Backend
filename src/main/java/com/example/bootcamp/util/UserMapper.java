package com.example.bootcamp.util;

import com.example.bootcamp.dto.UserDTO;
import com.example.bootcamp.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

        public UserDTO convertToDto(User user){
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setUsername(user.getUsername());
            userDTO.setName(user.getName());

            userDTO.setActive(user.getActive());
            userDTO.setPhoto_url(user.getPhoto_url());
            userDTO.setMail(user.getMail());


            if (user.getCenters() != null) {
                userDTO.setCenters(user.getCenters().getTitle());
                userDTO.setCenter_id(user.getCenters() != null ? user.getCenters().getId() : null);
            }

            else{
                userDTO.setCenters(null);
                userDTO.setCenter_id(null);
            }




            return userDTO;
        }
}

