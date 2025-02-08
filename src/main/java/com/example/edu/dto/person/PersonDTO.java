package com.example.edu.dto.person;

import com.example.edu.dto.authorities.AuthorityDTO;
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
    //    private DepartmentDTO department;
    private String departmentName;
    private List<AuthorityDTO> authorities;
}
