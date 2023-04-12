package com.example.Active.Learning.project.account.dto.request.user;

import com.example.Active.Learning.project.account.models.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String firstname;
    private String lastname;
    private String username;
    private String avatar;
    private String password;
    private String provider;
    private Set<ERole> roles;
}