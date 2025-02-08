package com.example.edu.utils.mappers;

import com.example.edu.dto.person.PersonDTO;
import com.example.edu.entity.Person;
import com.example.edu.utils.GlobalUtils;
import lombok.experimental.UtilityClass;

import java.util.Collection;
import java.util.List;

@UtilityClass
public class PersonMapper {
    public PersonDTO convertToDTO(Person person) {
        PersonDTO dto = new PersonDTO();

        dto.setId(person.getId());
        dto.setName(person.getName());
        dto.setUsername(person.getUsername());
        dto.setEmail(person.getEmail());
        dto.setPhotoUrl(person.getPhotoUrl());
        dto.setPhone(person.getPhone());
        dto.setInfo(person.getInfo());

        if (person.getDepartment() != null) dto.setDepartment(DepartmentMapper.convertToDTO(person.getDepartment()));
        dto.setAuthorities(AuthorityMapper.convertAllToDTO(person.getAuthorities()));

        return dto;
    }

    public List<PersonDTO> convertAllToDTO(Collection<Person> persons) {
        return GlobalUtils.convertAllToDTO(persons, PersonMapper::convertToDTO);
    }
}
