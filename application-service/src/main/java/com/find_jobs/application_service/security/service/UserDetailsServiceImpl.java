package com.find_jobs.application_service.security.service;


import com.find_jobs.application_service.client.AuthServiceClient;
import com.find_jobs.application_service.entity.User;
import com.find_jobs.application_service.util.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    AuthServiceClient authServiceClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Response<User> response = authServiceClient.getUserLogin();

        if (response.getResponseCode() == 200) {
            User user = response.getData();

            return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                    .password(user.getPassword())  // Password should be set if available, or use a default
                    .roles(user.getRole())  // Convert string role to SimpleGrantedAuthority
                    .build();
        } else {
            throw new UsernameNotFoundException("Failed to retrieve user data for username: " + username);
        }
    }
}