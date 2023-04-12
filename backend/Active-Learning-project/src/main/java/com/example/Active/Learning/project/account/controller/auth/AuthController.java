package com.example.Active.Learning.project.account.controller.auth;


import com.example.Active.Learning.project.account.dto.response.auth.AuthResponse;
import com.example.Active.Learning.project.constants.EndPoints;
import com.example.Active.Learning.project.account.dto.request.user.UserRequest;
import com.example.Active.Learning.project.account.dto.response.user.UserResponse;
import com.example.Active.Learning.project.account.service.auth.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(EndPoints.AUTH)
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthenticationServiceImpl authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody UserRequest userRequest) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(authenticationService.authenticate(userRequest));
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}