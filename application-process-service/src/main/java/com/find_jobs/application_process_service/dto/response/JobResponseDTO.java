package com.find_jobs.application_process_service.dto.response;

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
public class JobResponseDTO {
    private Long id;
    private String title;
    private String description;
}
