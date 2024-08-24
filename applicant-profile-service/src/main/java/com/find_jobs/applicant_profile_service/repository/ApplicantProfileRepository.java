package com.find_jobs.applicant_profile_service.repository;

import com.find_jobs.applicant_profile_service.entity.ApplicantProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicantProfileRepository extends JpaRepository<ApplicantProfile, Long> {

    Optional<ApplicantProfile> findByUserId(Long userId);

}