package com.find_jobs.application_process_service.dto.request;

import com.find_jobs.application_process_service.entity.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationStatusHistoryRequestDTO {
    private Long applicationId;
    private ApplicationStatus status;
    private Timestamp changedAt;
    private Long changedBy;
}
