package com.find_jobs.applicant_profile_service.repository;

import com.find_jobs.applicant_profile_service.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, Long> {
}
