package com.example.Active.Learning.project.account.exceptions.model;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorMessage {
    String statusCode;
    String message;

    public ErrorMessage(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
