package com.find_jobs.job_service.repository;

import com.find_jobs.job_service.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

}