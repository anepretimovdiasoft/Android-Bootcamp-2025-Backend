package com.example.bootcamp.service.impl;

import com.example.bootcamp.entity.Users;
import com.example.bootcamp.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Users> optionalUsers = usersRepository.findByUsername(s);

        if(optionalUsers.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return null;
    }
}
