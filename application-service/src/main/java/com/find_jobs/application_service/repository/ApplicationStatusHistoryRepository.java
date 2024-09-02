package com.find_jobs.application_service.repository;

import com.find_jobs.application_service.entity.ApplicationStatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationStatusHistoryRepository extends JpaRepository<ApplicationStatusHistory, Long> {


}
