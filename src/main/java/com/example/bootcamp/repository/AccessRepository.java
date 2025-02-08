package com.example.bootcamp.repository;

import com.example.bootcamp.entity.Access;
import com.example.bootcamp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccessRepository extends JpaRepository<Access, Long> {
    Optional<Access> findByAuthority(String authority);
}
