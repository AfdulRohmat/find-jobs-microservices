package com.find_jobs.application_process_service.dto.response;

import com.find_jobs.application_process_service.entity.ApplicationStatusHistory;
import com.find_jobs.application_process_service.entity.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationResponseDTO {
    private Long id;
    private Long applicantId;
    private JobResponseDTO job;
    private CompanyResponseDTO company;
    private ApplicationStatus status;
    private List<ApplicationStatusHistory> tracking;
    private Timestamp appliedAt;
    private Timestamp updatedAt;
    private String resumeUrl;
    private String coverLetter;
}
