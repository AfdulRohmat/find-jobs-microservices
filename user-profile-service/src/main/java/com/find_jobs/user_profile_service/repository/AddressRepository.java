package com.find_jobs.user_profile_service.repository;

import com.find_jobs.user_profile_service.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}