package com.find_jobs.application_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicationRequestDTO {
    private Long jobId;
    private Long applicantId;
    private Long companyId;
    private String resumeUrl;
    private String coverLetter;
}
