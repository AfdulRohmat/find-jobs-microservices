package com.find_jobs.user_profile_service.service;

import com.find_jobs.user_profile_service.client.UserServiceClient;
import com.find_jobs.user_profile_service.constant.Constant;
import com.find_jobs.user_profile_service.dto.request.AddressDTO;
import com.find_jobs.user_profile_service.dto.request.CareerHistoryDTO;
import com.find_jobs.user_profile_service.dto.request.EducationDTO;
import com.find_jobs.user_profile_service.dto.request.UserProfileRequestDTO;
import com.find_jobs.user_profile_service.dto.response.UserProfileResponseDTO;
import com.find_jobs.user_profile_service.entity.*;
import com.find_jobs.user_profile_service.exception.NotFoundException;
import com.find_jobs.user_profile_service.repository.AddressRepository;
import com.find_jobs.user_profile_service.repository.CareerHistoryRepository;
import com.find_jobs.user_profile_service.repository.EducationRepository;
import com.find_jobs.user_profile_service.repository.UserProfileRepository;
import com.find_jobs.user_profile_service.service.mapper.UserProfileMapper;
import com.find_jobs.user_profile_service.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;


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

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private UserProfileMapper userProfileMapper;

    @Transactional
    public Response<Object> addUserProfile(UserProfileRequestDTO dto) {
        Response<User> userCurrentlyLogin = userServiceClient.getUserLogin();

        UserProfile userProfile = new UserProfile();

        if (!Objects.equals(userCurrentlyLogin.getData().getId(), dto.getUserId())) {
            throw new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE);
        }

        userProfile.setUserId(dto.getUserId());
        userProfile.setPersonalSummary(dto.getPersonalSummary());
        userProfile.setCvUrl(dto.getCvUrl());

        UserProfile savedUserProfile = userProfileRepository.save(userProfile);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.CREATE_SUCCESS_MESSAGE)
                .data(savedUserProfile)
                .build();
    }

    @Transactional
    public Response<Object> updateUserProfile(Long id, UserProfileRequestDTO dto) {
        Response<User> userCurrentlyLogin = userServiceClient.getUserLogin();

        UserProfile userProfile = userProfileRepository.findById(id).orElseThrow(() -> new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE));

        if (!Objects.equals(userCurrentlyLogin.getData().getId(), dto.getUserId())) {
            throw new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE);
        }

        userProfile.setUserId(dto.getUserId());
        userProfile.setPersonalSummary(dto.getPersonalSummary());
        userProfile.setCvUrl(dto.getCvUrl());

        UserProfile savedUserProfile = userProfileRepository.save(userProfile);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.UPDATE_SUCCESS_MESSAGE)
                .data(savedUserProfile)
                .build();
    }

    @Transactional
    public Response<Object> addAddress(AddressDTO dto) {

        UserProfile userProfile = userProfileRepository.findById(dto.getUserProfileId())
                .orElseThrow(() -> new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE));

        Address address = new Address();

        address.setUserProfileId(userProfile.getId());
        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setPostalCode(dto.getPostalCode());
        address.setCountry(dto.getCountry());

        Address savedAddress = addressRepository.save(address);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.CREATE_SUCCESS_MESSAGE)
                .data(savedAddress)
                .build();
    }

    @Transactional
    public Response<Object> updateAddress(Long id, AddressDTO dto) {
        UserProfile userProfile = userProfileRepository.findById(dto.getUserProfileId())
                .orElseThrow(() -> new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE));

        Address address = addressRepository.findById(id).orElseThrow(() -> new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE));

        address.setUserProfileId(userProfile.getId());
        address.setStreet(dto.getStreet());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setPostalCode(dto.getPostalCode());
        address.setCountry(dto.getCountry());

        Address savedAddress = addressRepository.save(address);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.UPDATE_SUCCESS_MESSAGE)
                .data(savedAddress)
                .build();
    }

    @Transactional
    public Response<Object> addCareerHistory(CareerHistoryDTO dto) {
        UserProfile userProfile = userProfileRepository.findById(dto.getUserProfileId())
                .orElseThrow(() -> new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE));

        CareerHistory careerHistory = new CareerHistory();

        careerHistory.setUserProfileId(userProfile.getId());
        careerHistory.setJobTitle(dto.getJobTitle());
        careerHistory.setCompanyName(dto.getCompanyName());
        careerHistory.setStartDate(dto.getStartDate());
        careerHistory.setEndDate(dto.getEndDate());
        careerHistory.setResponsibilities(dto.getResponsibilities());
        careerHistory.setAchievements(dto.getAchievements());

        CareerHistory saveCareerHistory = careerHistoryRepository.save(careerHistory);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.CREATE_SUCCESS_MESSAGE)
                .data(saveCareerHistory)
                .build();
    }

    @Transactional
    public Response<Object> updateCareerHistory(Long id, CareerHistoryDTO dto) {
        UserProfile userProfile = userProfileRepository.findById(dto.getUserProfileId())
                .orElseThrow(() -> new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE));

        CareerHistory careerHistory = careerHistoryRepository.findById(id).orElseThrow(() -> new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE));

        careerHistory.setUserProfileId(userProfile.getId());
        careerHistory.setJobTitle(dto.getJobTitle());
        careerHistory.setCompanyName(dto.getCompanyName());
        careerHistory.setStartDate(dto.getStartDate());
        careerHistory.setEndDate(dto.getEndDate());
        careerHistory.setResponsibilities(dto.getResponsibilities());
        careerHistory.setAchievements(dto.getAchievements());

        CareerHistory saveCareerHistory = careerHistoryRepository.save(careerHistory);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.UPDATE_SUCCESS_MESSAGE)
                .data(saveCareerHistory)
                .build();
    }

    @Transactional
    public Response<Object> addEducation(EducationDTO dto) {
        UserProfile userProfile = userProfileRepository.findById(dto.getUserProfileId())
                .orElseThrow(() -> new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE));

        Education education = new Education();

        education.setUserProfileId(userProfile.getId());
        education.setInstitutionName(dto.getInstitutionName());
        education.setDegree(dto.getDegree());
        education.setFieldOfStudy(dto.getFieldOfStudy());
        education.setStartDate(dto.getStartDate());
        education.setEndDate(dto.getEndDate());
        education.setGrade(dto.getGrade());
        education.setAchievements(dto.getAchievements());

        Education saveEducation = educationRepository.save(education);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.CREATE_SUCCESS_MESSAGE)
                .data(saveEducation)
                .build();
    }

    @Transactional
    public Response<Object> updateEducation(Long id, EducationDTO dto) {
        UserProfile userProfile = userProfileRepository.findById(dto.getUserProfileId())
                .orElseThrow(() -> new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE));

        Education education = educationRepository.findById(id).orElseThrow(() -> new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE));

        education.setUserProfileId(userProfile.getId());
        education.setInstitutionName(dto.getInstitutionName());
        education.setDegree(dto.getDegree());
        education.setFieldOfStudy(dto.getFieldOfStudy());
        education.setStartDate(dto.getStartDate());
        education.setEndDate(dto.getEndDate());
        education.setGrade(dto.getGrade());
        education.setAchievements(dto.getAchievements());

        Education saveEducation = educationRepository.save(education);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.UPDATE_SUCCESS_MESSAGE)
                .data(saveEducation)
                .build();
    }

    @Transactional
    public Response<Object> getUserProfile(Long userId) {
        UserProfile userProfile = userProfileRepository.findByUserId(userId)
                .orElseThrow(() -> new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE));

        Address address = addressRepository.findByUserProfileId(userProfile.getId()).orElseThrow(() -> new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE));

        List<CareerHistory> careerHistories = careerHistoryRepository.findByUserProfileId(userProfile.getId());
        List<Education> educations = educationRepository.findByUserProfileId(userProfile.getId());

        UserProfileResponseDTO userProfileResponseDTO = userProfileMapper.mapToUserProfileResponseDTO(userProfile, address, careerHistories, educations);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(userProfileResponseDTO)
                .build();
    }

}
