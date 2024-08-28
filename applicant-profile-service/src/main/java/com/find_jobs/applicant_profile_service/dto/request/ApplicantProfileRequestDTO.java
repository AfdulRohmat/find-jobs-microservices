package com.find_jobs.applicant_profile_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApplicantProfileRequestDTO {
    private String personalSummary;
    private MultipartFile cvFile;
    private MultipartFile photoProfileFile;
}
