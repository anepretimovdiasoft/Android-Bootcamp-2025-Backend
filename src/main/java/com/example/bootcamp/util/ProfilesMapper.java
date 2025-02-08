package com.example.bootcamp.util;

import com.example.bootcamp.dto.ProfilesDTO;
import com.example.bootcamp.entity.Profile;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProfilesMapper {
    public static ProfilesDTO convertDTO(Profile profile) {
        ProfilesDTO profilesDTO = new ProfilesDTO();
        profilesDTO.setId(profile.getId());
        profilesDTO.setCenterId(profile.getCenter());
        profilesDTO.setName(profile.getName());
        profilesDTO.setLastname(profile.getLastname());
        profilesDTO.setAge(profile.getAge());
        profilesDTO.setPicture(profile.getPicture());
        profilesDTO.setBio(profile.getBio());
        return profilesDTO;
    }
}
