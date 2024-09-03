package com.find_jobs.application_process_service.service;

import com.find_jobs.application_process_service.entity.Application;
import com.find_jobs.application_process_service.entity.ApplicationStatusHistory;
import com.find_jobs.application_process_service.entity.enums.ApplicationStatus;
import com.find_jobs.application_process_service.repository.ApplicationStatusHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationStatusHistoryService {

    @Autowired
    ApplicationStatusHistoryRepository applicationStatusHistoryRepository;

    public void logApplicationStatusChange(Long applicationId, ApplicationStatus status, Long changedBy) {
        ApplicationStatusHistory history = ApplicationStatusHistory.builder()
                .application(Application.builder().id(applicationId).build())
                .status(status)
                .changedByUserId(changedBy)
                .build();

        applicationStatusHistoryRepository.save(history);
    }

    public List<ApplicationStatusHistory> getHistoryByApplicationId(Long applicationId) {
        return applicationStatusHistoryRepository.findByApplicationId(applicationId);
    }

}
