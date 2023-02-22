package com.example.Active.Learning.project.account.interfaces;

import com.example.Active.Learning.project.account.models.User;
import com.example.Active.Learning.project.account.payload.request.SignUpRequest;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IUserService {
    ResponseEntity<?> createUser(@NonNull SignUpRequest signUpRequest);

    ResponseEntity<List<User>> getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir);
}
