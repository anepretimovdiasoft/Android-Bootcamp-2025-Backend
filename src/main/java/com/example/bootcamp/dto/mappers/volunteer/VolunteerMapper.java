package com.example.bootcamp.dto.mappers.volunteer;

import com.example.bootcamp.domain.entity.Center;
import com.example.bootcamp.domain.entity.Role;
import com.example.bootcamp.domain.entity.Volunteer;
import com.example.bootcamp.dto.entity.volunteer.VolunteerDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VolunteerMapper {
    public static VolunteerDTO convertToDTO(Volunteer volunteer) {
        VolunteerDTO volunteerDTO = new VolunteerDTO();

        volunteerDTO.setId(volunteer.getId());
        volunteerDTO.setName(volunteer.getName());
        volunteerDTO.setSurname(volunteer.getSurname());
        volunteerDTO.setPatronymic(volunteer.getPatronymic());
        volunteerDTO.setAboutMe(volunteer.getAboutMe());
        volunteerDTO.setTelephone(volunteer.getTelephone());
        volunteerDTO.setEmail(volunteer.getEmail());
        volunteerDTO.setBirthday(volunteer.getBirthday());
        volunteerDTO.setCity(volunteer.getCity());
        volunteerDTO.setTelegramLink(volunteer.getTelegramLink());
        volunteerDTO.setVkLink(volunteer.getVkLink());
        volunteerDTO.setRole(volunteer.getRole().getRoleName());

        if (volunteer.getCenter() != null) {
            volunteerDTO.setCenter(volunteer.getCenter().getId());
            volunteerDTO.setCenterName(volunteer.getCenter().getName());
            volunteerDTO.setCenterImageUrl(volunteer.getCenter().getLinkLogo());
        }

        volunteerDTO.setProfileImageUrl(volunteer.getProfileImageUrl());
        volunteerDTO.setMedicalBook(volunteer.isMedicalBook());
        volunteerDTO.setDriverLicense(volunteer.isDriverLicense());

        return volunteerDTO;
    }

    public static Volunteer convertFromDTO(VolunteerDTO volunteerDTO, Role role, Center center) {
        Volunteer volunteer = new Volunteer();

        volunteer.setName(volunteerDTO.getName());
        volunteer.setSurname(volunteerDTO.getSurname());
        volunteer.setPatronymic(volunteerDTO.getPatronymic());
        volunteer.setAboutMe(volunteerDTO.getAboutMe());
        volunteer.setTelephone(volunteerDTO.getTelephone());
        volunteer.setEmail(volunteerDTO.getEmail());
        volunteer.setBirthday(volunteerDTO.getBirthday());
        volunteer.setCity(volunteerDTO.getCity());
        volunteer.setTelegramLink(volunteerDTO.getTelegramLink());
        volunteer.setVkLink(volunteerDTO.getVkLink());
        volunteer.setRole(role);
        if (center != null) volunteer.setCenter(center);
        volunteer.setProfileImageUrl(volunteerDTO.getProfileImageUrl());
        volunteer.setMedicalBook(volunteerDTO.isMedicalBook());
        volunteer.setDriverLicense(volunteerDTO.isDriverLicense());

        return volunteer;
    }
}
