package com.find_jobs.job_service.service;

import com.find_jobs.job_service.client.AuthServiceClient;
import com.find_jobs.job_service.client.CompanyServiceClient;
import com.find_jobs.job_service.constant.Constant;
import com.find_jobs.job_service.dto.request.JobRequestDTO;
import com.find_jobs.job_service.dto.response.JobResponseDTO;
import com.find_jobs.job_service.entity.CompanyProfile;
import com.find_jobs.job_service.entity.Job;
import com.find_jobs.job_service.entity.User;
import com.find_jobs.job_service.exception.NotFoundException;
import com.find_jobs.job_service.repository.JobRepository;
import com.find_jobs.job_service.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private AuthServiceClient authServiceClient;

    @Autowired
    private CompanyServiceClient companyServiceClient;


    @Transactional
    public Response<Object> createJob(JobRequestDTO jobRequestDTO) {
        Response<User> userCurrentlyLogin = authServiceClient.getUserLogin();

        Response<CompanyProfile> companyProfile = companyServiceClient.getCompanyById(jobRequestDTO.getCompanyId());
        if (companyProfile.getData() == null) {
            throw new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE);
        }


        Job job = new Job();

        job.setTitle(jobRequestDTO.getTitle());
        job.setDescription(jobRequestDTO.getDescription());
        job.setLocation(jobRequestDTO.getLocation());
        job.setCompanyId(companyProfile.getData().getId());
        job.setSalary(jobRequestDTO.getSalary());
        job.setEmploymentType(jobRequestDTO.getEmploymentType());
        job.setExperienceLevel(jobRequestDTO.getExperienceLevel());
        job.setYearsOfExperience(jobRequestDTO.getYearsOfExperience());
        job.setPostedDate(jobRequestDTO.getPostedDate());
        job.setExpiryDate(jobRequestDTO.getApplicationDeadline());
        job.setSkills(jobRequestDTO.getSkills());
        job.setIndustry(jobRequestDTO.getIndustry());
        job.setJobFunction(jobRequestDTO.getJobFunction());
        job.setEducationLevel(jobRequestDTO.getEducationLevel());

        job.setCreatedByUserId(userCurrentlyLogin.getData().getId());

        Job savedJob = jobRepository.save(job);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(savedJob)
                .build();
    }

    @Transactional
    public Response<Object> getAllJobs(int page, int size,  String search, String location, Long companyId, String employmentType) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Job> getAllJobs = jobRepository.searchJobs(search, location, companyId, employmentType, pageable);

        List<JobResponseDTO> jobResponseDTOs = getAllJobs.stream()
                .map(this::mapToJobResponseDTO)
                .toList();


        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(jobResponseDTOs)
                .pageNumber(getAllJobs.getNumber())
                .pageSize(getAllJobs.getSize())
                .totalPage(getAllJobs.getTotalPages())
                .totalData(getAllJobs.getTotalElements())
                .build();
    }


    @Transactional
    public Response<Object> getJobById(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new NotFoundException("Data not found"));

        Response<CompanyProfile> companyProfile = companyServiceClient.getCompanyById(job.getCompanyId());

        JobResponseDTO data = JobResponseDTO.builder()
                .title(job.getTitle())
                .description(job.getDescription())
                .location(job.getLocation())
                .companyId(job.getCompanyId())
                .salary(job.getSalary())
                .employmentType(job.getEmploymentType())
                .experienceLevel(job.getExperienceLevel())
                .yearsOfExperience(job.getYearsOfExperience())
                .postedDate(job.getPostedDate())
                .expiryDate(job.getExpiryDate())
                .skills(job.getSkills())
                .industry(job.getIndustry())
                .jobFunction(job.getJobFunction())
                .educationLevel(job.getEducationLevel())
                .companyId(job.getCompanyId())
                .company(companyProfile.getData())
                .build();

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(data)
                .build();
    }

    @Transactional
    public Response<Object> updateJob(Long id, JobRequestDTO jobRequestDTO) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new NotFoundException("Data not found"));

        Response<CompanyProfile> companyProfile = companyServiceClient.getCompanyById(jobRequestDTO.getCompanyId());
        if (companyProfile.getData() == null) {
            throw new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE);
        }

        job.setTitle(jobRequestDTO.getTitle());
        job.setDescription(jobRequestDTO.getDescription());
        job.setLocation(jobRequestDTO.getLocation());
        job.setCompanyId(companyProfile.getData().getId());
        job.setSalary(jobRequestDTO.getSalary());
        job.setEmploymentType(jobRequestDTO.getEmploymentType());
        job.setExperienceLevel(jobRequestDTO.getExperienceLevel());
        job.setYearsOfExperience(jobRequestDTO.getYearsOfExperience());
        job.setPostedDate(jobRequestDTO.getPostedDate());
        job.setExpiryDate(jobRequestDTO.getApplicationDeadline());
        job.setSkills(jobRequestDTO.getSkills());
        job.setIndustry(jobRequestDTO.getIndustry());
        job.setJobFunction(jobRequestDTO.getJobFunction());
        job.setEducationLevel(jobRequestDTO.getEducationLevel());

        Job updatedJob = jobRepository.save(job);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(updatedJob)
                .build();
    }

    @Transactional
    public Response<Object> deleteJob(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(() -> new NotFoundException("Data not found"));

        jobRepository.delete(job);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(null)
                .build();
    }

    private JobResponseDTO mapToJobResponseDTO(Job job) {

        Response<CompanyProfile> companyProfile = companyServiceClient.getCompanyById(job.getCompanyId());

        return JobResponseDTO.builder()
                .id(job.getId())
                .title(job.getTitle())
                .description(job.getDescription())
                .location(job.getLocation())
                .employmentType(job.getEmploymentType())
                .salary(job.getSalary())
                .experienceLevel(job.getExperienceLevel())
                .yearsOfExperience(job.getYearsOfExperience())
                .postedDate(job.getPostedDate())
                .expiryDate(job.getExpiryDate())
                .skills(job.getSkills())
                .industry(job.getIndustry())
                .jobFunction(job.getJobFunction())
                .educationLevel(job.getEducationLevel())
                .companyId(job.getCompanyId())
                .company(companyProfile.getData()) // Assuming Job has a reference to CompanyProfile
                .build();
    }
}
