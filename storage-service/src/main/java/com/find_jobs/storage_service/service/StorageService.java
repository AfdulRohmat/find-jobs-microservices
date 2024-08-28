package com.find_jobs.storage_service.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.find_jobs.storage_service.dto.response.CloudinaryUploadResponseDTO;
import com.find_jobs.storage_service.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StorageService {

    @Autowired
    private Cloudinary cloudinary;

    public Response<Object> uploadFile(MultipartFile file, String folder) {
        try {
            Map<String, Object> uploadParams = new HashMap<>();
            String fullPath = "find-jobs-microservice/" + folder;
            uploadParams.put("folder", fullPath);

            // Upload file to Cloudinary
            Map<String, Object> uploadResult = cloudinary.uploader().upload(file.getBytes(), uploadParams);

            // Convert the map to a CloudinaryUploadResponse object
            ObjectMapper objectMapper = new ObjectMapper();
            CloudinaryUploadResponseDTO cloudinaryUploadResponseDTO = objectMapper.convertValue(uploadResult, CloudinaryUploadResponseDTO.class);

            return Response.builder()
                    .responseCode(200)
                    .responseMessage("File uploaded successfully")
                    .data(cloudinaryUploadResponseDTO)
                    .build();

        } catch (IOException e) {
            List<String> errors = List.of("File upload failed", e.getMessage());

            return Response.builder()
                    .responseCode(500)
                    .responseMessage("Error")
                    .errorList(errors)
                    .build();
        }
    }

    // Method to delete a file
    public Response<Object> deleteFile(String publicId) {
        try {
            Map deleteResult = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());

            if ("ok".equals(deleteResult.get("result"))) {
                return Response.builder()
                        .responseCode(200)
                        .responseMessage("File deleted successfully")
                        .data(deleteResult)
                        .build();
            } else {
                return Response.builder()
                        .responseCode(400)
                        .responseMessage("Failed to delete file")
                        .data(deleteResult)
                        .build();
            }

        } catch (IOException e) {
            List<String> errors = List.of("File deletion failed", e.getMessage());

            return Response.builder()
                    .responseCode(500)
                    .responseMessage("Error")
                    .errorList(errors)
                    .build();
        }
    }


}
