package com.example.Active.Learning.project.authenticate.services;


import com.example.Active.Learning.project.account.exceptions.UserNotFoundException;
import com.example.Active.Learning.project.account.payload.response.JwtResponse;
import com.example.Active.Learning.project.account.payload.response.MessageResponse;
import com.example.Active.Learning.project.authenticate.interfaces.IAuthentication;
import com.example.Active.Learning.project.authenticate.payload.request.AuthRequest;
import com.example.Active.Learning.project.authenticate.payload.response.AuthResponse;
import com.example.Active.Learning.project.authenticate.security.jwt.JwtUtils;
import com.example.Active.Learning.project.authenticate.security.services.UserDetailsImpl;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthenticationServiceImpl implements IAuthentication {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Value("${active_learning_project.app.tokenType}")
    private String tokenType;


    @Override
    public ResponseEntity<?> authenticate(@NonNull AuthRequest authRequest) {

        Authentication authentication = getAuthentication(authRequest.getUsername(), authRequest.getPassword());

        if (authentication == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new UserNotFoundException(MessageResponse.USER_NOT_FOUND));
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        return ResponseEntity.ok(new AuthResponse(jwt,tokenType));
    }

    public Authentication getAuthentication(String username, String password) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,
                            password));
        } catch (AuthenticationException error) {
            return null;
        }
        return authentication;
    }

}
