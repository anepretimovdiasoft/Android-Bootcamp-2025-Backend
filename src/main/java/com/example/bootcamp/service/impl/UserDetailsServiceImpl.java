package com.example.bootcamp.service.impl;

import com.example.bootcamp.entity.Person;
import com.example.bootcamp.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Person> optionalPerson = personRepository.findByUsername(s);

        if(optionalPerson.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        return optionalPerson.get();
    }
}
