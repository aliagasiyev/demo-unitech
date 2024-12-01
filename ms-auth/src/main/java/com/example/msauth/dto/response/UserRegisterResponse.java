package com.example.msauth.dto.response;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterResponse {
    @NotBlank
    private Long id;

    @NotBlank
    private String fullName;

    @NotBlank
    private String fin;

    @NotBlank
    private String email;
}
