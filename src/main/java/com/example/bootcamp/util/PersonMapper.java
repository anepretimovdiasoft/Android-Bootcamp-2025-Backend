package com.example.bootcamp.util;

import com.example.bootcamp.dto.PersonDTO;
import com.example.bootcamp.dto.PersonGetDTO;
import com.example.bootcamp.entity.Person;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PersonMapper {
    public PersonDTO convertToDto(Person person){
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(person.getName());
        personDTO.setUsername(person.getUsername());
        personDTO.setEmail(person.getEmail());
        personDTO.setPhone(person.getPhone());
        personDTO.setPhotoUrl(person.getPhotoUrl());
        personDTO.setCoordinate_x(person.getCoordinate_x());
        personDTO.setCoordinate_y(person.getCoordinate_y());
        personDTO.setVolunteer(person.getVolunteer().getName());
        return personDTO;
    }

    public PersonGetDTO convertToDTO(Person person){
        PersonGetDTO personGetDTO = new PersonGetDTO();
        personGetDTO.setName(person.getName());
        return personGetDTO;
    }
}
