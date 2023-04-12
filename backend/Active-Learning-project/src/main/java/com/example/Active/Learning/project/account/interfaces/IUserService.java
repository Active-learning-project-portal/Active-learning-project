package com.example.Active.Learning.project.account.interfaces;

import com.example.Active.Learning.project.account.dto.request.user.UserRequest;
import com.example.Active.Learning.project.account.dto.response.auth.AuthResponse;
import com.example.Active.Learning.project.account.dto.response.user.UserResponse;
import lombok.NonNull;


import java.util.List;
import java.util.UUID;

public interface IUserService {
    AuthResponse saveUser(@NonNull UserRequest userRequest);
    List<UserResponse> findAllUsers();
    UserResponse findByUsername(@NonNull String username);
    UserResponse findUserById(@NonNull UUID userId);
    UserResponse updateUser(@NonNull UUID userId, @NonNull UserRequest userRequest);
    void deleteById(@NonNull UUID userId);
    void delete(@NonNull UserRequest userRequest);
}

