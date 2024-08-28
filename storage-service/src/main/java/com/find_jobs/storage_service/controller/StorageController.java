package com.find_jobs.storage_service.controller;

import com.find_jobs.storage_service.service.StorageService;
import com.find_jobs.storage_service.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<Object> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("folderName") String folderName) {

        return ResponseEntity.ok(storageService.uploadFile(file, folderName));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteFile(@RequestParam("publicId") String publicId) {

        return ResponseEntity.ok(storageService.deleteFile(publicId));
    }


}
