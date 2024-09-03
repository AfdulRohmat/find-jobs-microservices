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
public class CompanyResponseDTO {
    private Long id;
    private String name;
    private String description;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String profileImageUrl;
}
