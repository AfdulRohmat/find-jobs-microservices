package com.find_jobs.applicant_profile_service.repository;

import com.find_jobs.applicant_profile_service.entity.ApplicantAddress;
import com.find_jobs.applicant_profile_service.entity.ApplicantProfile;
import com.find_jobs.applicant_profile_service.entity.CareerHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CareerHistoryRepository extends JpaRepository<CareerHistory, Long> {
    Optional<CareerHistory> findByApplicantProfileId(ApplicantProfile applicantProfileId);
}
