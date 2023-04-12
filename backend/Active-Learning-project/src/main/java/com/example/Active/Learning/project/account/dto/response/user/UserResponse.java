package com.example.Active.Learning.project.account.dto.response.user;

import com.example.Active.Learning.project.account.dto.response.auth.AuthResponse;
import com.example.Active.Learning.project.account.models.enums.ERole;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponse {
    private UUID id;
    private String firstname;
    private String lastname;
    private String username;
    private String avatar;
    private String provider;
    private Set<ERole> roles;
}
