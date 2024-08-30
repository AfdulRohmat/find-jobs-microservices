package com.find_jobs.storage_service.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CloudinaryUploadResponseDTO {
    private String signature;
    private String format;
    private String resource_type;
    private String secure_url;
    private String created_at;
    private String asset_id;
    private String version_id;
    private String type;
    private String version;
    private String url;
    private String public_id;
    private String[] tags;
    private String folder;
    private String original_filename;
    private String api_key;
    private long bytes;
    private int width;
    private String etag;
    private boolean placeholder;
    private int height;

}
