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
public class EducationDTO {
    private String institutionName;
    private String degree;
    private String fieldOfStudy;
    private Date startDate;
    private Date endDate;
    private String grade;
    private String achievements;
}
