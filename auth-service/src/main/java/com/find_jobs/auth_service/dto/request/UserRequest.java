package com.find_jobs.auth_service.dto.request;

import com.find_jobs.auth_service.constant.Constant;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank(message = "Username is mandatory")
    @Size(min = 4, max = 50, message = "Username must be between 4 and 50 characters")
    private String username;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 3, message = "Password must be at least 3 characters")
    private String password;

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotBlank(message = "Role is mandatory")
    private String role;

    @NotBlank(message = "Gender is mandatory, please fill it!")
    @NotNull(message = "Gender is mandatory, please fill it!")
    @Pattern(regexp = Constant.Regex.ALPHABET, message = "Invalid format gender")
    private String gender;

    private Boolean isDeleted = false;
}
