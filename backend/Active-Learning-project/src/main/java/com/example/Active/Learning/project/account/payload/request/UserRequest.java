package com.example.Active.Learning.project.account.payload.request;

import com.example.Active.Learning.project.account.models.role.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class UserRequest {
    private String firstname;
    private String lastname;
    private String username;
    private String avatar;
    private String password;
    private String provider;
    private Set<Role> roles;
    private Date joined = new Date();
}