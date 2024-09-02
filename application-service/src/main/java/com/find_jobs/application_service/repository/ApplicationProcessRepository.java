package com.find_jobs.application_service.repository;


import com.find_jobs.application_service.entity.Application;
import com.find_jobs.application_service.entity.enums.ApplicationStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ApplicationProcessRepository extends JpaRepository<Application, Long> {

    @Query(value = "SELECT * FROM applications WHERE" +
            "(:status IS NULL OR status = :status) AND" +
            "applicantId = :applicantId",
            nativeQuery = true)
    Page<Application> getApplicationsByApplicantId(@Param("applicantId") Long applicantId,
                                                   @Param("status") ApplicationStatus status,
                                                   Pageable pageable);

    @Query(value = "SELECT * FROM applications WHERE" +
            "(:status IS NULL OR status = :status) AND" +
            "companyId = :companyId",
            nativeQuery = true)
    Page<Application> getApplicationsByCompanyId(@Param("companyId") Long companyId,
                                                 @Param("status") ApplicationStatus status,
                                                 Pageable pageable);
}
