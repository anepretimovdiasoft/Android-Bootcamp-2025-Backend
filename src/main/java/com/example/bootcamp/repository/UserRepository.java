package com.example.bootcamp.repository;

import com.example.bootcamp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}