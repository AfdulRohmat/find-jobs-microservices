package com.find_jobs.auth_service.repository;

import com.find_jobs.auth_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "SELECT * from users WHERE :username and deleted_at IS NULL", nativeQuery = true)
    Optional<User> findByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByUsername(String username);

    Optional<User> findFirstByEmail(String email);

}
