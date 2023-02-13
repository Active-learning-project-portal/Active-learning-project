package com.example.Active.Learning.project.account.exceptions;

public class UserNotFound extends  RuntimeException{
    public UserNotFound(final String errorMessage){
        super(errorMessage);
    }

}
