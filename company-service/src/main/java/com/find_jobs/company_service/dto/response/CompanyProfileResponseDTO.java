package com.find_jobs.company_service.dto.response;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Builder
public class CompanyProfileResponseDTO {
    private Long id;
    private String name;
    private String email;
    private String street;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private String website;
    private String description;
    private String industry;
    private String size;
    private String profileImageUrl;
}
