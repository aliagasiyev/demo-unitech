package com.example.msauth.controller;

import com.example.msauth.dto.request.UserLoginRequest;
import com.example.msauth.dto.request.UserRegisterRequest;
import com.example.msauth.dto.response.UserLoginResponse;
import com.example.msauth.dto.response.UserRegisterResponse;
import com.example.msauth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> register(@Valid @RequestBody UserRegisterRequest userRequest) {
        return ResponseEntity.ok(authService.register(userRequest));
    }
    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> login(@RequestBody UserLoginRequest request) {
        return ResponseEntity.ok(authService.loginUser(request));
    }
}