package com.example.bootcamp.service.impl;

import com.example.bootcamp.aspect.annotation.LogExample;
import com.example.bootcamp.domain.entity.Center;
import com.example.bootcamp.domain.entity.Role;
import com.example.bootcamp.domain.entity.Volunteer;
import com.example.bootcamp.domain.exception.ConflictResourceException;
import com.example.bootcamp.domain.exception.ResourceNotFoundException;
import com.example.bootcamp.repository.CenterRepository;
import com.example.bootcamp.repository.RoleRepository;
import com.example.bootcamp.repository.VolunteerRepository;
import com.example.bootcamp.service.VolunteerService;
import com.example.bootcamp.dto.entity.volunteer.VolunteerDTO;
import com.example.bootcamp.dto.entity.volunteer.VolunteerRegisterDTO;
import com.example.bootcamp.dto.mappers.volunteer.VolunteerMapper;
import com.example.bootcamp.dto.mappers.volunteer.VolunteerRegisterMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VolunteerServiceImpl implements VolunteerService {
    private final VolunteerRepository volunteerRepository;
    private final RoleRepository roleRepository;
    private final CenterRepository centerRepository;


    @Override
    @LogExample
    public VolunteerDTO getById(long id) {
        return volunteerRepository.findById(id)
                .map(VolunteerMapper::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Волонтёр с id (" + id + ") не найден!"));
    }

    @Override
    @LogExample
    public VolunteerDTO getByEmail(String email) {
        return volunteerRepository.findByEmail(email)
                .map(VolunteerMapper::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Волонтёр с email (" + email + ") не найден!"));
    }

    @Override
    @LogExample
    public VolunteerDTO getByTelephone(String telephone) {
        return volunteerRepository.findByTelephone(telephone)
                .map(VolunteerMapper::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Волонтёр с telephone (" + telephone + ") не найден!"));
    }

    @Override
    @LogExample
    public List<VolunteerDTO> getAll() {
        return volunteerRepository.findAll().stream()
                .map(VolunteerMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @LogExample
    public VolunteerDTO create(VolunteerRegisterDTO volunteerRegisterDTO) {
        Optional<Role> roleOptional = roleRepository.findByRoleName(volunteerRegisterDTO.getRole());

        Optional<Volunteer> volunteerEmailOptional = volunteerRepository.findByEmail(volunteerRegisterDTO.getEmail());
        Optional<Volunteer> volunteerPhoneOptional = volunteerRepository.findByTelephone(volunteerRegisterDTO.getTelephone());

        if (roleOptional.isEmpty())
            throw new ResourceNotFoundException("Роль с названием (" + volunteerRegisterDTO.getRole() + ") не найдена!");

        // todo: Кривая реализация, переписать
        Center center = null;
        if (volunteerRegisterDTO.getCenter() != null) {
            Optional<Center> centerOptional = centerRepository.findById(volunteerRegisterDTO.getCenter());

            if (centerOptional.isEmpty())
                throw new ResourceNotFoundException("Центр с id (" + volunteerRegisterDTO.getCenter() + ") не найден!");
            center = centerOptional.get();
        }

        if (volunteerEmailOptional.isPresent() || volunteerPhoneOptional.isPresent())
            throw new ConflictResourceException("Пользователь с таким email или телефоном уже существует!");

        Volunteer volunteer = VolunteerRegisterMapper.convertFromDTO(volunteerRegisterDTO, roleOptional.get(), center);
        return VolunteerMapper.convertToDTO(volunteerRepository.save(volunteer));
    }

    @Override
    @LogExample
    public VolunteerDTO update(long id, VolunteerDTO volunteerDTO) {
        Volunteer volunteer = volunteerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Волонтёр с id (" + id + ") не найден!"));

        // todo: Пока поля email, telephone и role обновлять нельзя, но над этим стоит ещё подумать.
        volunteer.setName(volunteerDTO.getName());
        volunteer.setSurname(volunteerDTO.getSurname());
        volunteer.setPatronymic(volunteerDTO.getPatronymic());
        volunteer.setAboutMe(volunteerDTO.getAboutMe());
        volunteer.setBirthday(volunteerDTO.getBirthday());
        volunteer.setCity(volunteerDTO.getCity());
        volunteer.setTelegramLink(volunteerDTO.getTelegramLink());
        volunteer.setVkLink(volunteerDTO.getVkLink());

        if (volunteerDTO.getCenter() == null) volunteer.setCenter(null);
        else centerRepository.findById(volunteerDTO.getCenter()).ifPresent(volunteer::setCenter);

        volunteer.setProfileImageUrl(volunteerDTO.getProfileImageUrl());
        volunteer.setMedicalBook(volunteerDTO.isMedicalBook());
        volunteer.setDriverLicense(volunteerDTO.isDriverLicense());

        return VolunteerMapper.convertToDTO(volunteerRepository.save(volunteer));
    }

    @Override
    @LogExample
    public void delete(long volunteerId) {
        volunteerRepository.findById(volunteerId)
                .orElseThrow(() -> new ResourceNotFoundException("Волонтёр с id (" + volunteerId + ") не найден!"));
        volunteerRepository.deleteById(volunteerId);
    }

    @Override
    @LogExample
    public List<VolunteerDTO> free() {
        return volunteerRepository.findAll().stream()
                .filter(volunteer -> volunteer.getCenter() == null)
                .map(VolunteerMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
