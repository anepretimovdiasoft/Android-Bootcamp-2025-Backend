package com.example.edu.dto.person;

import com.example.edu.dto.authorities.AuthorityDTO;
import com.example.edu.dto.department.DepartmentDTO;
import lombok.Data;

import java.util.List;

@Data
public class PersonDTO {
    private Long id;
    private String name;
    private String username;
    private String email;
    private String photoUrl;
    private String info;
    private String phone;
    private DepartmentDTO department;
    private List<AuthorityDTO> authorities;
}
