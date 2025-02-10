package com.example.bootcamp.repository;

import com.example.bootcamp.dto.AuthorityDTO;
import com.example.bootcamp.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Optional<Authority> findByAuthority(String Authority);
}
