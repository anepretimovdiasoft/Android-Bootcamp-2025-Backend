package com.example.bootcamp.dto.mappers.volunteer;

import com.example.bootcamp.domain.entity.Center;
import com.example.bootcamp.domain.entity.Role;
import com.example.bootcamp.domain.entity.Volunteer;
import com.example.bootcamp.dto.entity.volunteer.VolunteerRegisterDTO;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VolunteerRegisterMapper {
    public static Volunteer convertFromDTO(VolunteerRegisterDTO volunteerDTO, String password, Role role, Center center) {
        Volunteer volunteer = new Volunteer();

        volunteer.setName(volunteerDTO.getName());
        volunteer.setSurname(volunteerDTO.getSurname());
        volunteer.setPatronymic(volunteerDTO.getPatronymic());
        volunteer.setAboutMe(volunteerDTO.getAboutMe());
        volunteer.setTelephone(volunteerDTO.getTelephone());
        volunteer.setEmail(volunteerDTO.getEmail());
        volunteer.setPassword(password);
        volunteer.setBirthday(volunteerDTO.getBirthday());
        volunteer.setCity(volunteerDTO.getCity());
        volunteer.setTelegramLink(volunteerDTO.getTelegramLink());
        volunteer.setVkLink(volunteerDTO.getVkLink());
        volunteer.setRole(role);
        volunteer.setCenter(center);
        volunteer.setProfileImageUrl(null);
        volunteer.setMedicalBook(volunteerDTO.isMedicalBook());
        volunteer.setDriverLicense(volunteerDTO.isDriverLicense());

        return volunteer;
    }
}
