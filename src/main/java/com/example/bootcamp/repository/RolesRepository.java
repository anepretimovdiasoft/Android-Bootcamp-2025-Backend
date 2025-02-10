package com.example.bootcamp.repository;

import com.example.bootcamp.entity.Roles;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByRole(String role);

}
