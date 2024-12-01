package com.example.msauth.service;

import com.example.msauth.client.UserClient;
import com.example.msauth.dto.response.UserRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserClient userFeignClient;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserRegisterResponse userRegisterResponse = userFeignClient.getUserByEmail(email);

        if (userRegisterResponse == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }

        return new User(
                userRegisterResponse.getEmail(),
                "",
                Collections.emptyList() // Rollar əlavə edilə bilər
        );
    }
}
