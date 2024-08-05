package com.find_jobs.auth_service.dto.request;

import com.find_jobs.auth_service.constant.Constant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class LoginRequest {

    @NotBlank(message = "Username is mandatory, please fill it!")
    @NotNull(message = "Username mandatory, please fill it!")
    @Pattern(regexp = Constant.Regex.ALPHANUMERIC, message = "Invalid format username")
    private String username;

    @NotBlank(message = "Password is mandatory, please fill it!")
    @NotNull(message = "Password mandatory, please fill it!")
    private String password;

}