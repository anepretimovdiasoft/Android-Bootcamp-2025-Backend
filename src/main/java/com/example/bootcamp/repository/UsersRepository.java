package com.example.bootcamp.repository;

import com.example.bootcamp.entity.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByCredentialsId(Integer credentialsId);
    Optional<Users> findByAuthorityId(Integer authorityId);
    Optional<Users> findByUsername(String username);
    Optional<Users> findByProfileId(Integer profileId);


    Page<Users> findAll(Pageable pageable);
}
