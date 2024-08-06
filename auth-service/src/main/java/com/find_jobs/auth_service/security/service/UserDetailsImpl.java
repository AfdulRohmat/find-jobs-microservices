package com.find_jobs.auth_service.security.service;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.find_jobs.auth_service.entity.Role;
import com.find_jobs.auth_service.entity.User;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Getter
    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
//        return Collections.emptyList();
    }

    public static UserDetailsImpl build(User user) {

//        GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().name());
        List<GrantedAuthority> authorities = Stream.of(user.getRole())
                .map(x -> new SimpleGrantedAuthority("ROLE_" + x.name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                authorities);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }

}