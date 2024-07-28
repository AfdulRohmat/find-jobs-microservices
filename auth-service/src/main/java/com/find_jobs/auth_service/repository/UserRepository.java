package com.find_jobs.auth_service.repository;

import com.find_jobs.auth_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from users where username =?1 and is_deleted = false", nativeQuery = true)
    User findByUsername(String username);
}