package com.example.Active.Learning.project.account.models.enums;

public enum ERole {
    ROLE_SUPER_ADMIN("SUPER_ADMIN"),
    ROLE_ADMIN("ADMIN"),
    ROLE_TRAINER("TRAINER"),
    ROLE_TRAINEE("TRAINEE");

    private final String roleName;
    ERole(String roleName){
        this.roleName = roleName;
    }
}
