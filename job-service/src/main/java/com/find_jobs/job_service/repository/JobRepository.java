package com.find_jobs.job_service.repository;

import com.find_jobs.job_service.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JobRepository extends JpaRepository<Job, Long> {
    @Query(value = "SELECT * FROM job WHERE " +
            "(:search IS NULL OR title ILIKE %:search%) AND " +
            "(:location IS NULL OR location ILIKE %:location%) AND " +
            "(:companyId IS NULL OR company_id = :companyId) AND " +
            "(:employmentType IS NULL OR employment_type ILIKE %:employmentType%)",
            nativeQuery = true)
    Page<Job> searchJobs(@Param("search") String search,
                         @Param("location") String location,
                         @Param("companyId") Long companyId,
                         @Param("employmentType") String employmentType,
                         Pageable pageable);
}