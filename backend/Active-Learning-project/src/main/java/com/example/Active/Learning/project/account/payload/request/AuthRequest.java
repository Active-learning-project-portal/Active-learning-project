package com.example.Active.Learning.project.authenticate.payload.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}