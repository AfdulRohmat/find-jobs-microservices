package com.find_jobs.auth_service.controller;

import com.find_jobs.auth_service.dto.request.LoginRequest;
import com.find_jobs.auth_service.dto.request.UserRequest;
import com.find_jobs.auth_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<Object> register(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.register(userRequest));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }

    @GetMapping(value = "/validateAccessToken")
    public ResponseEntity<Object> validateAccessToken(@RequestParam(value = "accessToken", defaultValue = "") String accessToken) {
        return ResponseEntity.ok(userService.validateAccessToken(accessToken));
    }

    @GetMapping(value = "/user")
    public ResponseEntity<Object> getUser() {
        return ResponseEntity.ok(userService.getUser());
    }

    @GetMapping(value = "/test")
    public ResponseEntity<Object> doTest() {
        return ResponseEntity.ok("Success Test");
    }

}
