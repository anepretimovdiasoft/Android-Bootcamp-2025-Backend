package com.example.bootcamp.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String role;
    private String first_name;
    private String last_name;
    private String email;
    private String contact_info;
    private String biography;
    private String photo;
    private Long status;
}
