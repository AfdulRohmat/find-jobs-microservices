package com.find_jobs.auth_service.service;


import com.find_jobs.auth_service.constant.Constant;
import com.find_jobs.auth_service.dto.response.RegisterResponseDTO;
import com.find_jobs.auth_service.dto.response.UserResponseDTO;
import com.find_jobs.auth_service.dto.request.LoginRequestDTO;
import com.find_jobs.auth_service.dto.request.RegisterRequestDTO;
import com.find_jobs.auth_service.dto.response.LoginResponseDTO;
import com.find_jobs.auth_service.entity.User;
import com.find_jobs.auth_service.exception.DataExistException;
import com.find_jobs.auth_service.exception.NotFoundException;
import com.find_jobs.auth_service.repository.UserRepository;
import com.find_jobs.auth_service.security.jwt.JwtUtils;
import com.find_jobs.auth_service.security.service.UserDetailsImpl;
import com.find_jobs.auth_service.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.net.http.HttpResponse;
import java.util.*;

@Service
@Slf4j
public class AuthService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    PasswordEncoder encoder;

    @Value("${jwt.expirationMs}")
    private int jwtExpirationMs;

    @Value("${user.role}")
    private String userRole;

    @Value("${user.gender}")
    private String userGender;

    Date nowDate = new Date();

    @Transactional
    public Response<Object> register(RegisterRequestDTO registerRequestDTO) {
        if (userRepository.existsByUsername(registerRequestDTO.getUsername())) {
            throw new DataExistException(Constant.Message.EXIST_DATA_MESSAGE);
        }

        if (userRepository.existsByEmail(registerRequestDTO.getEmail())) {
            throw new DataExistException(Constant.Message.EXIST_DATA_MESSAGE);
        }

        // generate bcrypt password
        String hashedPassword = encoder.encode(registerRequestDTO.getPassword());

        User user = new User();

        user.setUsername(registerRequestDTO.getUsername());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(hashedPassword);
        user.setRole(registerRequestDTO.getRole());

        userRepository.save(user);

        RegisterResponseDTO registerResponseDTO = RegisterResponseDTO.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .build();

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(registerResponseDTO)
                .build();
    }


    @Transactional
    public Response<Object> login(LoginRequestDTO loginRequestDTO) {

        userRepository.findFirstByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new DataExistException(Constant.Message.INVALID_LOGIN_MESSAGE));

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()));

        log.info("authentication = {}", authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String jwt = jwtUtils.generateToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        User user = userRepository.findFirstByEmail(userDetails.getEmail()).orElseThrow(() -> new DataExistException(Constant.Message.NOT_FOUND_DATA_MESSAGE));

        LoginResponseDTO loginResponseDTO = LoginResponseDTO.builder()
                .userId(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .accessToken(jwt)
                .role(roles)
                .build();

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(loginResponseDTO)
                .build();
    }


    @Transactional
    public Response<Object> getUserLogin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        User user = userRepository.findById(userDetails.getId()).orElseThrow(() -> new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE));

        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .build();

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(userResponseDTO)
                .build();

    }

    @Transactional
    public Response<Object> getUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(Constant.Message.NOT_FOUND_DATA_MESSAGE));

        UserResponseDTO userResponseDTO = UserResponseDTO.builder()
                .id(user.getId())
                .email(user.getEmail())
                .username(user.getUsername())
                .role(user.getRole())
                .build();

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(userResponseDTO)
                .build();
    }
}