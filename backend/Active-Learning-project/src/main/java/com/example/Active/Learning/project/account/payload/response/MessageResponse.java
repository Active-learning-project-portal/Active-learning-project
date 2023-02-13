package com.example.Active.Learning.project.account.payload.response;

public class MessageResponse extends RuntimeException{
    public static final String EMAIL_ALREADY_EXISTS = "Error: Username is already taken!";
    public static final String ROLE_NOT_FOUND_ERROR ="Error: Role is not found.";
    public static final  String USERNAME_ALREADY_EXISTS = "Error: Username is already taken!";
    public static final String USER_CREATED_SUCCESSFULLY = "User registered successfully!";

    public static final String USER_NOT_FOUND = "ERROR: User not Found!";
}
