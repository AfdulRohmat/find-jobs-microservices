package com.find_jobs.company_service.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCompanyProfileRequestDTO {
    private Long companyId;

    private MultipartFile companyLogoFile;

    private MultipartFile bannerFile;

    private String companyName;

    private String description;

    private String organizationType;

    private String industryType;

    private String teamSize;

    private Integer yearsOfEstablishment;

    private String companyWebsite;

    private String companyVision;

    private List<String> socialMediaLinks;

    private String address;

    private String phoneContact;

    private String companyEmail;
}
