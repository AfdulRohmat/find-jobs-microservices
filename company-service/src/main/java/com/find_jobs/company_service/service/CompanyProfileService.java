package com.find_jobs.company_service.service;

import com.find_jobs.company_service.constant.Constant;
import com.find_jobs.company_service.dto.request.CompanyProfileRequestDTO;
import com.find_jobs.company_service.entity.CompanyProfile;
import com.find_jobs.company_service.exception.NotFoundException;
import com.find_jobs.company_service.repository.CompanyProfileRepository;
import com.find_jobs.company_service.service.mapper.CompanyProfileMapper;
import com.find_jobs.company_service.util.Response;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyProfileService {

    @Autowired
    private CompanyProfileRepository companyProfileRepository;

    @Autowired
    private CompanyProfileMapper companyProfileMapper;

    @Transactional
    public Response<Object> createCompanyProfile(CompanyProfileRequestDTO companyProfileRequestDTO) {
        CompanyProfile companyProfile = new CompanyProfile();

        companyProfile.setName(companyProfileRequestDTO.getName());
        companyProfile.setStreet(companyProfileRequestDTO.getStreet());
        companyProfile.setCity(companyProfileRequestDTO.getCity());
        companyProfile.setState(companyProfileRequestDTO.getState());
        companyProfile.setPostalCode(companyProfileRequestDTO.getPostalCode());
        companyProfile.setCountry(companyProfileRequestDTO.getCountry());
        companyProfile.setWebsite(companyProfileRequestDTO.getWebsite());
        companyProfile.setDescription(companyProfileRequestDTO.getDescription());
        companyProfile.setIndustry(companyProfileRequestDTO.getIndustry());
        companyProfile.setSize(companyProfileRequestDTO.getSize());
        companyProfile.setProfileImageUrl(companyProfileRequestDTO.getProfileImageUrl());

        CompanyProfile savedCompanyProfile = companyProfileRepository.save(companyProfile);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.CREATE_SUCCESS_MESSAGE)
                .data(savedCompanyProfile)
                .build();
    }


    @Transactional
    public Response<Object> getCompanyProfile(Long companyId) {
        CompanyProfile companyProfile = companyProfileRepository.findById(companyId)
                .orElseThrow(() -> new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE));

//        mapToCompanyProfileResponseDTO(companyProfile);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(companyProfile)
                .build();
    }

}
