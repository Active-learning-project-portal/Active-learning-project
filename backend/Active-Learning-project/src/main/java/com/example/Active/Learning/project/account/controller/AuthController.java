package com.example.Active.Learning.project.account.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


import com.example.Active.Learning.project.account.models.ERole;
import com.example.Active.Learning.project.account.models.Role;
import com.example.Active.Learning.project.account.models.User;
import com.example.Active.Learning.project.account.payload.request.SignInRequest;
import com.example.Active.Learning.project.account.payload.request.SignUpRequest;
import com.example.Active.Learning.project.account.payload.response.JwtResponse;
import com.example.Active.Learning.project.account.payload.response.MessageResponse;
import com.example.Active.Learning.project.account.repositories.RoleRepository;
import com.example.Active.Learning.project.account.repositories.UserRepository;
import com.example.Active.Learning.project.account.security.jwt.JwtUtils;
import com.example.Active.Learning.project.account.security.services.UserDetailsImpl;
import com.example.Active.Learning.project.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody SignInRequest signInRequest) {
        return userService.authenticateUser(signInRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        return userService.saveUser(signUpRequest);
    }
}