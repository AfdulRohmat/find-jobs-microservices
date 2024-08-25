package com.find_jobs.company_service.repository;

import com.find_jobs.company_service.entity.CompanyProfile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyProfileRepository extends JpaRepository<CompanyProfile, Long> {
    Optional<CompanyProfile> findByCreatedByUserId(Long userId);
}