package com.example.Active.Learning.project.account.exceptions;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(final String errorMessage) {
        super(errorMessage);
    }
}