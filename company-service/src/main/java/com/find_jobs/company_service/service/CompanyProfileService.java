package com.find_jobs.company_service.service;

import com.find_jobs.company_service.client.AuthServiceClient;
import com.find_jobs.company_service.constant.Constant;
import com.find_jobs.company_service.dto.request.CompanyProfileRequestDTO;
import com.find_jobs.company_service.entity.CompanyProfile;
import com.find_jobs.company_service.entity.User;
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

    @Autowired
    private AuthServiceClient authServiceClient;

    @Transactional
    public Response<Object> createCompanyProfile(CompanyProfileRequestDTO companyProfileRequestDTO) {
        Response<User> userCurrentlyLogin = authServiceClient.getUserLogin();

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
        companyProfile.setCreatedByUserId(userCurrentlyLogin.getData().getId());

        CompanyProfile savedCompanyProfile = companyProfileRepository.save(companyProfile);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.CREATE_SUCCESS_MESSAGE)
                .data(savedCompanyProfile)
                .build();
    }


    @Transactional
    public Response<Object> getCompanyProfile() {

        Response<User> userCurrentlyLogin = authServiceClient.getUserLogin();

        CompanyProfile companyProfile = companyProfileRepository.findByCreatedByUserId(userCurrentlyLogin.getData().getId())
                .orElseThrow(() -> new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE));

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(companyProfile)
                .build();
    }

    @Transactional
    public Response<Object> getCompanyProfileById(Long companyId) {

        CompanyProfile companyProfile = companyProfileRepository.findById(companyId)
                .orElseThrow(() -> new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE));

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(companyProfile)
                .build();
    }

}
