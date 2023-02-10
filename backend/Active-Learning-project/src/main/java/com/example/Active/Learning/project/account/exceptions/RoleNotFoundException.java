package com.example.Active.Learning.project.account.exceptions;

public class RoleNotFoundException extends RuntimeException {
    public RoleNotFoundException(final String errorMessage) {
        super(errorMessage);
    }
}
