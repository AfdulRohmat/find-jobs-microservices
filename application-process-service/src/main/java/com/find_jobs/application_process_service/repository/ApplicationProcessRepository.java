package com.find_jobs.application_process_service.repository;


import com.find_jobs.application_process_service.entity.Application;
import com.find_jobs.application_process_service.entity.enums.ApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApplicationProcessRepository extends JpaRepository<Application, Long> {

    @Query(value = "SELECT * FROM applications WHERE " +
            "applicant_id = :applicantId AND " +
            "(:status IS NULL OR status = :status)",
            nativeQuery = true)
    Page<Application> getApplicationsByApplicantId(@Param("applicantId") Long applicantId,
                                                   @Param("status") String status,
                                                   Pageable pageable);

    @Query(value = "SELECT * FROM applications WHERE" +
            "(:status IS NULL OR status = :status) AND " +
            "company_id = :companyId",
            nativeQuery = true)
    Page<Application> getApplicationsByCompanyId(@Param("companyId") Long companyId,
                                                 @Param("status") String status,
                                                 Pageable pageable);
}
