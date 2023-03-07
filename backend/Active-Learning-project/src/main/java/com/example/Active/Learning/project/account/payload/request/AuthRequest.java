package com.example.Active.Learning.project.account.payload.request;

import com.example.Active.Learning.project.account.models.EAuthType;
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
    @NotBlank
    private EAuthType authType;
}