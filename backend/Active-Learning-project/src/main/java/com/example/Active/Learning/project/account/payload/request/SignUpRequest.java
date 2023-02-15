package com.example.Active.Learning.project.account.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SignUpRequest {
    private Long id;
    private boolean emailVerified;
    private String name;
    private String pictureUrl;
    private String locale;
    private String familyName;
    private String givenName;
    private String email;
    private String username;
    private String password;
    private Set<String> role;
    private String provider;

    private String authType;

    @Override
    public String toString() {
        return "SignUpRequest{" +
                "id=" + id +
                ", emailVerified=" + emailVerified +
                ", name='" + name + '\'' +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", locale='" + locale + '\'' +
                ", familyName='" + familyName + '\'' +
                ", givenName='" + givenName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", provider='" + provider + '\'' +
                '}';
    }

}