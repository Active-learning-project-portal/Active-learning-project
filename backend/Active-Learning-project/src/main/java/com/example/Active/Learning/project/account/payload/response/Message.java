package com.example.Active.Learning.project.account.payload.response;

public class Message {

    private Integer statusCode;
    private String message;

    public Message(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
