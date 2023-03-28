package com.example.Active.Learning.project.account.payload.response;

import com.example.Active.Learning.project.account.models.role.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
public class UserResponse {

    private String firstname;
    private String lastname;
    private String username;
    private String avatar;
    private String password;
    private String provider;
    private Set<Role> roles;
    private Date joined;

    private AuthResponse authResponse;

    public UserResponse(String firstname, String lastname, String username, String avatar, String provider, Set<Role> roles, Date joined,AuthResponse authResponse) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.username = username;
        this.avatar = avatar;
        this.provider = provider;
        this.roles = roles;
        this.joined = joined;
        this.authResponse = authResponse;
    }
}
