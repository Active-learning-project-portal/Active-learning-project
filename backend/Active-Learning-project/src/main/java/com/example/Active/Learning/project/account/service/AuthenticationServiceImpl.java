package com.example.Active.Learning.project.account.service;

import com.example.Active.Learning.project.account.models.users.User;
import com.example.Active.Learning.project.account.payload.request.UserRequest;
import com.example.Active.Learning.project.account.payload.response.MessageResponse;
import com.example.Active.Learning.project.account.payload.response.UserResponse;
import com.example.Active.Learning.project.account.security.jwt.JwtUtils;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Value("${active_learning_project.app.tokenType}")
    private String tokenType;

    @Autowired
    UserServiceImpl userService;


    public UserResponse authenticate(@NonNull UserRequest userRequest) {

        Authentication authentication = getAuthentication(userRequest.getUsername(), userRequest.getPassword());

        if (authentication == null) {
                return switch (userRequest.getProvider()) {
                    case "GITHUB", "GOOGLE" -> userService.saveUser(userRequest);
                    case  "MANUAL" -> throw new RuntimeException(MessageResponse.USER_NOT_FOUND);
                    default -> throw new RuntimeException(String.format("Unauthorized user {}", userRequest.getUsername()));
                };
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        User user = userService.findByUsername(userRequest.getUsername());
        user.setToken(jwt);
        user.setTokenType(tokenType);
        return  userService.mapUserToUserResponse(user);
    }

    public Authentication getAuthentication(String username, String password) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,
                            password));
        } catch (AuthenticationException error) {
            throw new RuntimeException(error.getMessage());
        }
        return authentication;
    }

}
