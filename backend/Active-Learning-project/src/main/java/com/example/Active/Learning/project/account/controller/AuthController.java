package com.example.Active.Learning.project.account.controller;


import com.example.Active.Learning.project.account.models.users.User;
import com.example.Active.Learning.project.account.payload.request.UserRequest;
import com.example.Active.Learning.project.account.payload.response.UserResponse;
import com.example.Active.Learning.project.account.service.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody UserRequest userRequest) {
        UserResponse userResponse = null;
        try {
            userResponse = authenticationService.authenticate(userRequest);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse);
    }
}