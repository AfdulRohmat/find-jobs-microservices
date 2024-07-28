package com.find_jobs.user_profile_service.repository;

import com.find_jobs.user_profile_service.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}