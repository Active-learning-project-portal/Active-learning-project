package com.example.Active.Learning.project.account.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class AuthResponse {

    private String token;

    private String tokenType;

    public AuthResponse(String token, String tokenType) {
        this.token = token;
        this.tokenType = tokenType;
    }

}
