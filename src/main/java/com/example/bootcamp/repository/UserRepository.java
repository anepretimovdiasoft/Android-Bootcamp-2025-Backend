package com.example.bootcamp.repository;

import com.example.bootcamp.entity.User;

import com.example.bootcamp.entity.Volunteer_centers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    List<User> findByActive(boolean active);



    List<User> findByActiveTrueAndCenters_id(Long center_id);


    @Modifying
    @Query(value = "DELETE FROM user_roles WHERE user_id = ?", nativeQuery = true)
    void deleteUserRoleByUserId(long id);

    @Modifying
    @Query(value = "DELETE FROM user WHERE id = ?1", nativeQuery = true)
    void deleteUserById(long id);

}
