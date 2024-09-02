package com.find_jobs.application_service.dto.response;

import com.find_jobs.application_service.entity.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationResponseDTO {
    private Long id;
    private Long jobId;
    private Long applicantId;
    private ApplicationStatus status;
    private Timestamp appliedAt;
    private Timestamp updatedAt;
    private String resumeUrl;
    private String coverLetter;
}
