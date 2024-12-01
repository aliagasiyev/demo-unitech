package com.example.msauth.service;

import com.example.msauth.client.UserClient;
import com.example.msauth.dto.response.UserRegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserClient userFeignClient;
    public Optional<UserRegisterResponse> findUserByEmail(String email){
        return Optional.ofNullable(userFeignClient.getUserByEmail(email));
    }

}
