package com.find_jobs.auth_service.security.service;

import com.find_jobs.auth_service.client.UserServiceClient;
import com.find_jobs.auth_service.constant.Constant;
import com.find_jobs.auth_service.dto.response.UserResponseDTO;
import com.find_jobs.auth_service.entity.User;
import com.find_jobs.auth_service.repository.UserRepository;
import com.find_jobs.auth_service.util.Response;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findFirstByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Email Not Found with email: " + email));

        return UserDetailsImpl.build(user);
    }
}