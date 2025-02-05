package com.example.bootcamp.repository;

import com.example.bootcamp.entity.Organization;
import com.example.bootcamp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String username);
    boolean existsByTelegramUsername(String username);

    User findByEmail(String username);
    List<User> findAllByOrganization(Organization organization);

    @Override
    @EntityGraph(attributePaths = {"authorities", "organization"})
    Page<User> findAll(Pageable pageable);
}
