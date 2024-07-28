package com.find_jobs.user_profile_service.service;

import com.find_jobs.user_profile_service.dto.request.CareerHistoryDTO;
import com.find_jobs.user_profile_service.dto.request.EducationDTO;
import com.find_jobs.user_profile_service.dto.request.UserProfileRequestDTO;
import com.find_jobs.user_profile_service.entity.Address;
import com.find_jobs.user_profile_service.entity.CareerHistory;
import com.find_jobs.user_profile_service.entity.Education;
import com.find_jobs.user_profile_service.entity.UserProfile;
import com.find_jobs.user_profile_service.repository.AddressRepository;
import com.find_jobs.user_profile_service.repository.CareerHistoryRepository;
import com.find_jobs.user_profile_service.repository.EducationRepository;
import com.find_jobs.user_profile_service.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CareerHistoryRepository careerHistoryRepository;

    @Autowired
    private EducationRepository educationRepository;

    @Transactional
    public UserProfile createUserProfile(UserProfileRequestDTO dto) {
        UserProfile userProfile = new UserProfile();
        userProfile.setUserId(dto.getUserId());
        userProfile.setPersonalSummary(dto.getPersonalSummary());
        userProfile.setCvUrl(dto.getCvUrl());

        UserProfile savedUserProfile = userProfileRepository.save(userProfile);

        if (dto.getAddress() != null) {
            Address address = new Address();
            address.setUserProfileId(savedUserProfile.getId());
            address.setStreet(dto.getAddress().getStreet());
            address.setCity(dto.getAddress().getCity());
            address.setState(dto.getAddress().getState());
            address.setPostalCode(dto.getAddress().getPostalCode());
            address.setCountry(dto.getAddress().getCountry());
            addressRepository.save(address);
        }

        if (dto.getCareerHistory() != null) {
            for (CareerHistoryDTO chDto : dto.getCareerHistory()) {
                CareerHistory careerHistory = getCareerHistory(chDto, savedUserProfile);
                careerHistoryRepository.save(careerHistory);
            }
        }

        if (dto.getEducation() != null) {
            for (EducationDTO eduDto : dto.getEducation()) {
                Education education = getEducation(eduDto, savedUserProfile);
                educationRepository.save(education);
            }
        }

        return savedUserProfile;
    }

    private static Education getEducation(EducationDTO eduDto, UserProfile savedUserProfile) {
        Education education = new Education();

        education.setUserProfileId(savedUserProfile.getId());
        education.setInstitutionName(eduDto.getInstitutionName());
        education.setDegree(eduDto.getDegree());
        education.setFieldOfStudy(eduDto.getFieldOfStudy());
        education.setStartDate(eduDto.getStartDate());
        education.setEndDate(eduDto.getEndDate());
        education.setGrade(eduDto.getGrade());
        education.setAchievements(eduDto.getAchievements());

        return education;
    }

    private static CareerHistory getCareerHistory(CareerHistoryDTO chDto, UserProfile savedUserProfile) {
        CareerHistory careerHistory = new CareerHistory();

        careerHistory.setUserProfileId(savedUserProfile.getId());
        careerHistory.setJobTitle(chDto.getJobTitle());
        careerHistory.setCompanyName(chDto.getCompanyName());
        careerHistory.setStartDate(chDto.getStartDate());
        careerHistory.setEndDate(chDto.getEndDate());
        careerHistory.setResponsibilities(chDto.getResponsibilities());
        careerHistory.setAchievements(chDto.getAchievements());

        return careerHistory;
    }
}
