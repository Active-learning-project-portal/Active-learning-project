package com.example.Active.Learning.project.account.service.auth;

import com.example.Active.Learning.project.account.dto.request.user.UserRequest;
import com.example.Active.Learning.project.account.dto.response.auth.AuthResponse;
import com.example.Active.Learning.project.account.dto.response.user.UserResponse;
import com.example.Active.Learning.project.account.models.users.User;
import com.example.Active.Learning.project.security.jwt.JwtUtils;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
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

    public AuthResponse authenticate(@NonNull UserRequest userRequest) {

        Authentication authentication = getAuthentication(userRequest.getUsername(), userRequest.getPassword());
        if (authentication == null) {
             throw new RuntimeException(String.format("Unauthorized user "+ userRequest.getUsername()));
        }
        if(authentication.isAuthenticated()){
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);
            return  new AuthResponse(jwt,tokenType);
        }
        throw new RuntimeException("User not authenticated");
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
