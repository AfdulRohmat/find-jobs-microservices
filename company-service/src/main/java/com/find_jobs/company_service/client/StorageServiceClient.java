package com.find_jobs.company_service.client;

import com.find_jobs.company_service.dto.response.CloudinaryUploadResponseDTO;
import com.find_jobs.company_service.util.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(name = "STORAGE-SERVICE", path = "/api/v1/storage")
public interface StorageServiceClient {

    @PostMapping("/upload")
    Response<CloudinaryUploadResponseDTO> uploadFile(@RequestParam("file") MultipartFile file,
                                                     @RequestParam("folderName") String folderName);
}