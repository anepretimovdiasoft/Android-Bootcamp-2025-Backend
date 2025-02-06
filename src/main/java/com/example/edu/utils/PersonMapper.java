package com.example.edu.utils;

import com.example.edu.dto.PersonDTO;
import com.example.edu.entity.Person;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PersonMapper {
    public PersonDTO convertToDTO(Person person) {
        PersonDTO dto = new PersonDTO();
        dto.setId(person.getId());
        dto.setName(person.getName());
        dto.setUsername(person.getUsername());
        dto.setEmail(person.getEmail());
        dto.setPhotoUrl(person.getPhotoUrl());
        dto.setDepartmentName(person.getDepartment().getName());
        return dto;
    }
}
