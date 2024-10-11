package com.find_jobs.job_service.repository;

import com.find_jobs.job_service.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JobRepository extends JpaRepository<Job, Long> {
    @Query(value = "SELECT * FROM job WHERE " +
            "(:jobTitle IS NULL OR job_title ILIKE %:jobTitle%) AND " +
            "(:locationCountry IS NULL OR location_country ILIKE %:locationCountry%) AND " +
            "(:locationCity IS NULL OR location_city ILIKE %:locationCity%) AND " +
            "(:companyId IS NULL OR company_id = :companyId) AND " +
            "(:jobType IS NULL OR job_type ILIKE %:jobType%) AND " +
            "deleted_at IS NULL ",
            nativeQuery = true)
    Page<Job> searchJobs(@Param("jobTitle") String jobTitle,
                         @Param("locationCountry") String locationCountry,
                         @Param("locationCity") String locationCity,
                         @Param("companyId") Long companyId,
                         @Param("jobType") String jobType,
                         Pageable pageable);

    @Query(value = "Select * from job where " +
            "company_id = :companyId", nativeQuery = true)
    Page<Job> getJobPostedByCompany(
            @Param("companyId") Long companyId,
            Pageable pageable);

    Optional<Job> findByIdAndDeletedAtIsNull(Long id);

    Optional<Job> findByCompanyIdAndDeletedAtIsNull(Long id);
}