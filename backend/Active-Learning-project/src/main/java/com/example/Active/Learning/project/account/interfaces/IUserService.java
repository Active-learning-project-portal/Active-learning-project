package com.example.Active.Learning.project.account.interfaces;

import com.example.Active.Learning.project.account.models.users.User;
import com.example.Active.Learning.project.account.payload.request.SignUpRequest;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface IUserService {
    ResponseEntity<?> createUser(@NonNull SignUpRequest signUpRequest);

    ResponseEntity<List<User>> getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir,String column);
    ResponseEntity<?> getUserById(@NonNull UUID userId);
    ResponseEntity<List<User>> updateUser(@NonNull UUID userId, @NonNull SignUpRequest signUpRequest);
    ResponseEntity<User> deleteUser(@NonNull UUID userId);
}

