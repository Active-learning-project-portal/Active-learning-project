package com.example.Active.Learning.project.account.payload.response;


import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class ErrorMessageResponse {
    HttpStatus statusCode;
    String message;

    public ErrorMessageResponse(HttpStatus statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
