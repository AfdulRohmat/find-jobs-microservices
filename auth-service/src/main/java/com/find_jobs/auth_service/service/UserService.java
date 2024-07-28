package com.find_jobs.auth_service.service;


import com.find_jobs.auth_service.constant.Constant;
import com.find_jobs.auth_service.dto.request.LoginRequest;
import com.find_jobs.auth_service.dto.request.UserRequest;
import com.find_jobs.auth_service.dto.response.LoginResponse;
import com.find_jobs.auth_service.entity.User;
import com.find_jobs.auth_service.exception.BadRequestCustomException;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

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
    public Response<Object> register(UserRequest userRequest) {
        User user = userRepository.findByUsername(userRequest.getUsername());
        if (Objects.nonNull(user)) {
            throw new DataExistException(Constant.Message.EXIST_DATA_MESSAGE);
        }

        if (!isValid(userRole, userRequest.getRole())) {
            throw new BadRequestCustomException(Constant.Message.FORBIDDEN_REQUEST_MESSAGE.replace("{value}", "role"));
        }

        if (!isValid(userGender, userRequest.getGender())) {
            throw new BadRequestCustomException(Constant.Message.FORBIDDEN_REQUEST_MESSAGE.replace("{value}", "gender"));
        }

        User savedUser = userRepository.save(mappingUser(userRequest));

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(savedUser)
                .build();

    }


    public Response<Object> login(LoginRequest loginRequest) {

        User user = userRepository.findByUsername(loginRequest.getUsername());
        if (Objects.isNull(user)) {
            throw new NotFoundException(Constant.Message.INVALID_LOGIN_MESSAGE);
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        LoginResponse response = mappingLoginResponse(user, jwt);

        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_MESSAGE)
                .data(response)
                .build();
    }

    public Response<Object> validateAccessToken(String accessToken) {
        boolean isValid = jwtUtils.validateJwtToken(accessToken);
        if (!isValid) {
            throw new BadRequestCustomException(Constant.Message.INVALID_TOKEN_MESSAGE);
        }
        return Response.builder()
                .responseCode(Constant.Response.SUCCESS_CODE)
                .responseMessage(Constant.Response.SUCCESS_VALID_TOKEN_MESSAGE)
                .build();
    }

    private LoginResponse mappingLoginResponse(User user, String jwt) {
        return LoginResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .accessToken(jwt)
                .tokenType("Bearer")
                .expiresIn(jwtExpirationMs)
                .role(user.getRole())
                .build();
    }

    private User mappingUser(UserRequest userRequestDTO) {
        // generate bcrypt password
        String hashedPassword = encoder.encode(userRequestDTO.getPassword());

        return User.builder()
                .username(userRequestDTO.getUsername())
                .email(userRequestDTO.getEmail())
                .password(hashedPassword)
                .firstName(userRequestDTO.getFirstName())
                .lastName(userRequestDTO.getLastName())
                .role(userRequestDTO.getRole())
                .gender(userRequestDTO.getGender())
                .isDeleted(userRequestDTO.getIsDeleted())
                .build();
    }

    private boolean isValid(String value, String request) {
        String[] valueList = value.split("\\|");
        List<String> reqList = new ArrayList<>();
        for (int i = 0; i < valueList.length; i++) {
            if (valueList[i].equals(request)) {
                reqList.add(request);
                break;
            }
        }
        if (reqList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

}