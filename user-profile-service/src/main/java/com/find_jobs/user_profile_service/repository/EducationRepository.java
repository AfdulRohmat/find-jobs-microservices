package com.find_jobs.user_profile_service.repository;

import com.find_jobs.user_profile_service.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationRepository extends JpaRepository<Education, Long> {
}