package com.example.bootcamp.util;

import com.example.bootcamp.dto.ProfilesDTO;
import com.example.bootcamp.entity.Profile;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProfilesMapper {
    public static ProfilesDTO convertDTO(Profile profile) {
        ProfilesDTO profilesDTO = new ProfilesDTO();

        if (profile.getCenter() != null) {
            profilesDTO.setCenterId(profile.getCenter().getId());
        }
        if (profile.getName() != null) {
            profilesDTO.setName(profile.getName());
        }
        if (profile.getLastname() != null) {
            profilesDTO.setLastname(profile.getLastname());
        }
        if (profile.getAge() != null) {
            profilesDTO.setAge(profile.getAge());
        }
        if (profile.getPicture() != null) {
            profilesDTO.setPicture(profile.getPicture());
        }
        if (profile.getBio() != null) {
            profilesDTO.setBio(profile.getBio());
        }
        if (profile.getEmail() != null) {
            profilesDTO.setEmail(profile.getEmail());
        }
        if (profile.getPhone() != null) {
            profilesDTO.setPhone(profile.getPhone());
        }

        return profilesDTO;
    }
}
