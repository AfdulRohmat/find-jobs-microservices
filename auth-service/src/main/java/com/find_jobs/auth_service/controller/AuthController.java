package com.find_jobs.auth_service.controller;

import com.find_jobs.auth_service.dto.request.LoginRequestDTO;
import com.find_jobs.auth_service.dto.request.RegisterRequestDTO;
import com.find_jobs.auth_service.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<Object> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        return ResponseEntity.ok(authService.register(registerRequestDTO));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }

    @GetMapping(value = "/user")
    public ResponseEntity<Object> getUser() {
        return ResponseEntity.ok(authService.getUserLogin());
    }

    @GetMapping(value = "/test")
    @PreAuthorize("hasRole('APPLICANT')")
    public ResponseEntity<Object> doTest() {
        return ResponseEntity.ok("Success Test");
    }

}
