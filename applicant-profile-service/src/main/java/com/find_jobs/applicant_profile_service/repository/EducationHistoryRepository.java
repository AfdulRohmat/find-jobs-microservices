package com.find_jobs.applicant_profile_service.repository;


import com.find_jobs.applicant_profile_service.entity.EducationHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationHistoryRepository extends JpaRepository<EducationHistory, Long> {
}
