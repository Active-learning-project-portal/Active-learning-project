package com.example.Active.Learning.project.account.service;


import com.example.Active.Learning.project.account.constants.DefaultValues;
import com.example.Active.Learning.project.account.exceptions.CourseNotFoundException;
import com.example.Active.Learning.project.account.exceptions.RoleNotFoundException;
import com.example.Active.Learning.project.account.exceptions.UserAlreadyRegisteredException;

import com.example.Active.Learning.project.account.exceptions.UserNotFoundException;
import com.example.Active.Learning.project.account.interfaces.IUserService;
import com.example.Active.Learning.project.account.models.course.Course;
import com.example.Active.Learning.project.account.models.role.Role;
import com.example.Active.Learning.project.account.models.users.User;
import com.example.Active.Learning.project.account.payload.request.SignUpRequest;
import com.example.Active.Learning.project.account.payload.response.MessageResponse;
import com.example.Active.Learning.project.account.repositories.CourseRepository;
import com.example.Active.Learning.project.account.repositories.RoleRepository;
import com.example.Active.Learning.project.account.repositories.UserRepository;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl{

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ServiceImpl<User> userService;

    public ResponseEntity<User> createUser(@NonNull SignUpRequest signUpRequest) {
        User user = new User(
                signUpRequest.getUsername(),
                signUpRequest.getFirstname(),
                signUpRequest.getLastname(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getProvider(),
                signUpRequest.getAvatar(),
                new Date());

        try {
            userService.create(user);
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage());
        }
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<List<User>> getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir, String searchValue) {
        return userService.findAll(pageNo,pageSize,sortBy,sortDir,searchValue);
    }

    public ResponseEntity<User> getUserById(@NonNull UUID id) {
        return userService.findById(id);
    }

    public ResponseEntity<User> updateUser(@NonNull UUID userId, @NonNull SignUpRequest signUpRequest) {
        User user = userRepository.findById(userId).
                orElseThrow(() ->
                        new UserNotFoundException(MessageResponse.USER_NOT_FOUND));
          user.setAvatar(signUpRequest.getAvatar());
          user.setFirstname(signUpRequest.getFirstname());
          user.setLastname(signUpRequest.getLastname());
          user.setPassword(signUpRequest.getPassword());
          user.setProvider(signUpRequest.getProvider());
          user.setLastname(signUpRequest.getLastname());
        return userService.update(user,userId);
    }

    public ResponseEntity<Void> deleteUser(@NonNull UUID userId) {
      return userService.delete(userId);
    }
}
