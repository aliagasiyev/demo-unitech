package com.example.msauth.client;

import com.example.msauth.dto.request.UserRegisterRequest;
import com.example.msauth.dto.response.UserRegisterResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ms-user", url = "http://localhost:8082")
public interface UserClient {

    @PostMapping("/api/users")
    UserRegisterResponse saveUser(@RequestBody UserRegisterRequest userRegisterRequest);

    @GetMapping("/{email}")
    UserRegisterResponse getUserByEmail(@PathVariable("email") String email);
}
