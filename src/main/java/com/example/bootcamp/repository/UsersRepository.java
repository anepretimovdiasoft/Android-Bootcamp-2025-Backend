package com.example.bootcamp.repository;

import com.example.bootcamp.modal.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByCredentialsId(Integer credentialsId);
    Optional<Users> findByRoleId(Integer roleId);
    Optional<Users> findByProfileId(Integer profileId);
}
