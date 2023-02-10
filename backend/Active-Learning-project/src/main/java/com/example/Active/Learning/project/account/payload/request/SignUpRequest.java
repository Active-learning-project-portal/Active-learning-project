package com.example.Active.Learning.project.account.payload.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class SignUpRequest{
    private Long id;
    private boolean emailVerified;
    private String name;
    private String pictureUrl;
    private String locale;
    private String familyName;
    private String givenName;
    private String email;
    private  String username;
    private String password ;
    private Set<String> role;
}