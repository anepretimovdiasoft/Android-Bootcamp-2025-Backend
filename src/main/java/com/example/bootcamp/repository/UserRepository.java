package com.example.bootcamp.repository;

import com.example.bootcamp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByCenterId(Long centerId);

    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);
}
