package com.example.msauth.service;

import com.example.msauth.client.UserClient;
import com.example.msauth.dto.request.UserLoginRequest;
import com.example.msauth.dto.request.UserRegisterRequest;
import com.example.msauth.dto.response.UserLoginResponse;
import com.example.msauth.dto.response.UserRegisterResponse;
import com.example.msauth.util.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserClient userFeignClient;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public UserLoginResponse loginUser(UserLoginRequest loginRequest) {
        UserRegisterResponse user = userFeignClient.getUserByEmail(loginRequest.getEmail());
        if (user == null || !passwordEncoder.matches(loginRequest.getPassword(), loginRequest.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        // Autentifikasiya üçün token yaradılır
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword()
        );
        authenticationManager.authenticate(authenticationToken);

        String token = jwtService.generateToken(user.getEmail());
        return new UserLoginResponse(token);
    }

    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) {
        userRegisterRequest.setPassword(passwordEncoder.encode(userRegisterRequest.getPassword()));
        return userFeignClient.saveUser(userRegisterRequest);
    }

    private UserLoginResponse authenticateLogin(UserLoginRequest userLoginRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userLoginRequest.getEmail(), userLoginRequest.getPassword()
        );
        authenticationManager.authenticate(authenticationToken);

        String token = jwtService.generateToken(userLoginRequest.getEmail());
        return new UserLoginResponse(token);
    }
}

