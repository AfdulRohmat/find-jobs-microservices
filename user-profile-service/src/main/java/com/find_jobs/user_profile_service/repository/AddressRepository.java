package com.find_jobs.user_profile_service.repository;

import com.find_jobs.user_profile_service.entity.UserProfileAddress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<UserProfileAddress, Long> {
    Optional<UserProfileAddress> findByUserProfileId(Long userProfileId);

}