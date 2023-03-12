package com.example.Active.Learning.project.account.exceptions.model;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class ErrorMessage {
    HttpStatus statusCode;
    String message;

    public ErrorMessage(HttpStatus statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
