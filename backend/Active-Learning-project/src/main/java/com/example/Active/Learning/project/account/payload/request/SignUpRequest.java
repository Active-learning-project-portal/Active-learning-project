package com.example.Active.Learning.project.account.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class SignUpRequest {
    private String name;
    private String lastname;
    private String username;
    private String avatar;
    private String password;
    private String provider;
    private Date joined = new Date();
}