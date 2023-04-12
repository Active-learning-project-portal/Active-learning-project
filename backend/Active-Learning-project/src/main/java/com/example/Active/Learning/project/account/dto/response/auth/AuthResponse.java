package com.example.Active.Learning.project.account.dto.response.auth;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AuthResponse {

    private String token;

    private String tokenType;

    public AuthResponse(String token, String tokenType) {
        this.token = token;
        this.tokenType = tokenType;
    }

}
