package com.example.edu.service.impl;

import com.example.edu.entity.Person;
import com.example.edu.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
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
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Person> optionalPerson = personRepository.findByUsername(userName);

        if (optionalPerson.isEmpty()) throw new UsernameNotFoundException("User not found");

        return optionalPerson.get();
    }
}
