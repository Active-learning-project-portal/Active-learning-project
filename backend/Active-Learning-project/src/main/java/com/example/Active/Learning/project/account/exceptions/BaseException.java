package com.example.Active.Learning.project.account.exceptions;

import com.example.Active.Learning.project.account.payload.response.ErrorMessageResponse;

public class BaseException extends RuntimeException{
    public BaseException(final ErrorMessageResponse messageResponse){
       super(String.valueOf(messageResponse));
    }
}
