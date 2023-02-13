package com.example.Active.Learning.project.account.exceptions;

public class userNotFoundException extends RuntimeException {
    public userNotFoundException(final String errorMessage) {
        super(errorMessage);
    }
}

