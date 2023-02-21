package com.example.Active.Learning.project.account.interfaces;

import com.example.Active.Learning.project.account.models.User;
import com.example.Active.Learning.project.account.payload.request.SignInRequest;
import com.example.Active.Learning.project.account.payload.request.SignUpRequest;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface UserService {
    ResponseEntity<?> createUser(@NonNull SignUpRequest signUpRequest);
    ResponseEntity<?> authenticateUser(@RequestBody SignInRequest signInRequest);
    ResponseEntity<List<User>> getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir);
}
