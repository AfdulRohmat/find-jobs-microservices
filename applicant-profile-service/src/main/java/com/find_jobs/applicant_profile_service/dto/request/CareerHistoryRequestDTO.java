package com.find_jobs.applicant_profile_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CareerHistoryRequestDTO {
    private Long applicantProfileId;

    private String companyName;
    private String position;
    private String startDate;
    private String endDate;
    private Boolean isCurrentPosition = false;
    private String description;
}
