package com.find_jobs.user_profile_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CareerHistoryDTO {
    private Long userProfileId;
    private String jobTitle;
    private String companyName;
    private Date startDate;
    private Date endDate;
    private String responsibilities;
    private String achievements;
}
