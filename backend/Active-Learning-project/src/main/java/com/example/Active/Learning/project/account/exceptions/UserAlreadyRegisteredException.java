package com.example.Active.Learning.project.account.exceptions;

public class UserAlreadyRegisteredException extends  RuntimeException{
    public UserAlreadyRegisteredException(final String errorMessage){
        super(errorMessage);
    }

}
