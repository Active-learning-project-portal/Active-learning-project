package com.example.Active.Learning.project.account.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(final String errorMessage) {
        super(errorMessage);
    }
}

