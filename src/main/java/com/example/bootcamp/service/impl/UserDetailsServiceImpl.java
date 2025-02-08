package com.example.bootcamp.service.impl;

import com.example.bootcamp.entity.Volunteer;
import com.example.bootcamp.repository.VolunteerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final VolunteerRepository volunteerRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<Volunteer> volunteerOptional = volunteerRepository.findByUsername(s);

        if(volunteerOptional.isEmpty()){
            throw new UsernameNotFoundException("User with username "+ s +" not found");
        }

        return volunteerOptional.get();
    }
}
