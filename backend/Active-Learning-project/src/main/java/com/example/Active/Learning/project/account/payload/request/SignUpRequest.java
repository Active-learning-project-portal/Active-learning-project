package com.example.Active.Learning.project.account.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SignUpRequest {
    private String username;
    private String password;
    private String provider;
    private String firstname;
    private String lastname;
    private String avatar;
    private String authType;
}