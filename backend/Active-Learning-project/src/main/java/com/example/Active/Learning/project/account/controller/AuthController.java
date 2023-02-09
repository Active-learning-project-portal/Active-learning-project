package com.example.Active.Learning.project.account.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/auth")
@RestController
public class AuthController {

    AuthController(){}

    @PostMapping("/signup")
    public void signUp(){}

    @PostMapping("/signin")
    public void signIn(){}

    @PostMapping("/signout")
    public void signOut(){}

}
