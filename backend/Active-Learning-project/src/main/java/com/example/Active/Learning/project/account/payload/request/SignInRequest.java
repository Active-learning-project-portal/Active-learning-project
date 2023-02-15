package com.example.Active.Learning.project.account.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * SignInRequest
 */
@Setter
@Getter
public class SignInRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private String provider;
    private String authType;
}