package com.find_jobs.user_profile_service.repository;

import com.find_jobs.user_profile_service.entity.CareerHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CareerHistoryRepository extends JpaRepository<CareerHistory, Long> {
}