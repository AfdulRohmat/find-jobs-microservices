package com.find_jobs.job_service.service;

import com.find_jobs.job_service.client.AuthServiceClient;
import com.find_jobs.job_service.client.CompanyServiceClient;
import com.find_jobs.job_service.constant.Constant;
import com.find_jobs.job_service.dto.request.JobRequestDTO;
import com.find_jobs.job_service.dto.request.UpdateJobRequestDTO;
import com.find_jobs.job_service.dto.response.JobResponseDTO;
import com.find_jobs.job_service.dto.response.CompanyProfileResponseDTO;
import com.find_jobs.job_service.entity.Job;
import com.find_jobs.job_service.dto.response.UserResponseDTO;
import com.find_jobs.job_service.exception.NotFoundException;
import com.find_jobs.job_service.repository.JobRepository;
import com.find_jobs.job_service.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

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
        Response<UserResponseDTO> userCurrentlyLogin = authServiceClient.getUserLogin();

        Response<CompanyProfileResponseDTO> companyProfile = companyServiceClient.getCompanyById(jobRequestDTO.getCompanyId());
        if (companyProfile.getData() == null) {
            throw new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE);
        }

        Job job = Job.builder()
                .jobTitle(jobRequestDTO.getJobTitle())
                .tags(jobRequestDTO.getTags())
                .jobRole(jobRequestDTO.getJobRole())
                .minSalary(jobRequestDTO.getMinSalary())
                .maxSalary(jobRequestDTO.getMaxSalary())
                .salaryType(jobRequestDTO.getSalaryType())
                .education(jobRequestDTO.getEducation())
                .experience(jobRequestDTO.getExperience())
                .jobType(jobRequestDTO.getJobType())
                .vacancies(jobRequestDTO.getVacancies())
                .skills(jobRequestDTO.getSkills())
                .expirationDate(jobRequestDTO.getExpirationDate())
                .jobLevel(jobRequestDTO.getJobLevel())
                .locationCountry(jobRequestDTO.getLocationCountry())
                .locationCity(jobRequestDTO.getLocationCity())
                .enableRemote(jobRequestDTO.getEnableRemote())
                .jobBenefits(jobRequestDTO.getJobBenefits())
                .jobDescription(jobRequestDTO.getJobDescription())
                .companyId(jobRequestDTO.getCompanyId())
                .createdByUserId(userCurrentlyLogin.getData().getId())
                .build();

        Job savedJob = jobRepository.save(job);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(savedJob)
                .build();
    }

    @Transactional
    public Response<Object> getAllJobs(int page, int size,
                                       String jobTitle,
                                       String locationCountry,
                                       String locationCity,
                                       Long companyId,
                                       String jobType) {

        Pageable pageable = PageRequest.of(page, size);

        Page<Job> getAllJobs = jobRepository.searchJobs(
                jobTitle, locationCountry, locationCity, companyId, jobType, pageable);

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
    public Response<Object> getJobPostedByCompany(int page, int size,
                                                  Long companyId) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Job> getJobsPostedByCompany = jobRepository.getJobPostedByCompany(companyId, pageable);

        List<JobResponseDTO> jobResponseDTOs = getJobsPostedByCompany.stream()
                .map(this::mapToJobResponseDTO)
                .toList();

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(jobResponseDTOs)
                .pageNumber(getJobsPostedByCompany.getNumber())
                .pageSize(getJobsPostedByCompany.getSize())
                .totalPage(getJobsPostedByCompany.getTotalPages())
                .totalData(getJobsPostedByCompany.getTotalElements())
                .build();
    }


    @Transactional
    public Response<Object> getJobById(Long jobId) {
        Job job = jobRepository.findByIdAndDeletedAtIsNull(jobId).orElseThrow(() -> new NotFoundException("Data not found"));

        Response<CompanyProfileResponseDTO> companyProfile = companyServiceClient.getCompanyById(job.getCompanyId());

        JobResponseDTO data = JobResponseDTO.builder()
                .id(job.getId())
                .jobTitle(job.getJobTitle())
                .tags(job.getTags())
                .jobRole(job.getJobRole())
                .minSalary(job.getMinSalary())
                .maxSalary(job.getMaxSalary())
                .salaryType(job.getSalaryType())
                .education(job.getEducation())
                .experience(job.getExperience())
                .jobType(job.getJobType())
                .vacancies(job.getVacancies())
                .skills(job.getSkills())
                .expirationDate(job.getExpirationDate())
                .jobLevel(job.getJobLevel())
                .locationCountry(job.getLocationCountry())
                .locationCity(job.getLocationCity())
                .enableRemote(job.getEnableRemote())
                .jobBenefits(job.getJobBenefits())
                .jobDescription(job.getJobDescription())
                .companyId(companyProfile.getData())
                .createdByUserId(job.getCreatedByUserId())
                .build();

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(data)
                .build();
    }

    @Transactional
    public Response<Object> updateJob(Long id, UpdateJobRequestDTO updateJobRequestDTO) {
        Job job = jobRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(() -> new NotFoundException("Data not found"));

        Response<CompanyProfileResponseDTO> companyProfile = companyServiceClient.getCompanyById(updateJobRequestDTO.getCompanyId());
        if (companyProfile.getData() == null) {
            throw new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE);
        }

        job.setJobTitle(updateJobRequestDTO.getJobTitle());
        job.setTags(updateJobRequestDTO.getTags());
        job.setJobRole(updateJobRequestDTO.getJobRole());
        job.setMinSalary(updateJobRequestDTO.getMinSalary());
        job.setMaxSalary(updateJobRequestDTO.getMaxSalary());
        job.setSalaryType(updateJobRequestDTO.getSalaryType());
        job.setEducation(updateJobRequestDTO.getEducation());
        job.setExperience(updateJobRequestDTO.getExperience());
        job.setJobType(updateJobRequestDTO.getJobType());
        job.setVacancies(updateJobRequestDTO.getVacancies());
        job.setSkills(updateJobRequestDTO.getSkills());
        job.setExpirationDate(updateJobRequestDTO.getExpirationDate());
        job.setJobLevel(updateJobRequestDTO.getJobLevel());
        job.setLocationCountry(updateJobRequestDTO.getLocationCountry());
        job.setLocationCity(updateJobRequestDTO.getLocationCity());
        job.setEnableRemote(updateJobRequestDTO.getEnableRemote());
        job.setJobBenefits(updateJobRequestDTO.getJobBenefits());
        job.setJobDescription(updateJobRequestDTO.getJobDescription());

        Job updatedJob = jobRepository.save(job);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(updatedJob)
                .build();
    }

    @Transactional
    public Response<Object> deleteJob(Long id) {
        Job job = jobRepository.findByIdAndDeletedAtIsNull(id).orElseThrow(() -> new NotFoundException("Data not found"));

        job.setDeletedAt(new Timestamp(System.currentTimeMillis()));
        jobRepository.save(job);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(null)
                .build();
    }

    private JobResponseDTO mapToJobResponseDTO(Job job) {

        Response<CompanyProfileResponseDTO> companyProfile = companyServiceClient.getCompanyById(job.getCompanyId());

        return JobResponseDTO.builder()
                .id(job.getId())
                .jobTitle(job.getJobTitle())
                .tags(job.getTags())
                .jobRole(job.getJobRole())
                .minSalary(job.getMinSalary())
                .maxSalary(job.getMaxSalary())
                .salaryType(job.getSalaryType())
                .education(job.getEducation())
                .experience(job.getExperience())
                .jobType(job.getJobType())
                .vacancies(job.getVacancies())
                .skills(job.getSkills())
                .expirationDate(job.getExpirationDate())
                .jobLevel(job.getJobLevel())
                .locationCountry(job.getLocationCountry())
                .locationCity(job.getLocationCity())
                .enableRemote(job.getEnableRemote())
                .jobBenefits(job.getJobBenefits())
                .jobDescription(job.getJobDescription())
                .companyId(companyProfile.getData())
                .createdByUserId(job.getCreatedByUserId())
                .build();
    }
}
