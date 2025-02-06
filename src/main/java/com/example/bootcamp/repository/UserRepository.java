package com.example.bootcamp.repository;

import com.example.bootcamp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @EntityGraph(attributePaths = {"organization", "authorities"})
    List<User> findAll();

    Optional<User> findByUsername(String username);

    @Override
    Page<User> findAll(Pageable pageable);
}
