package com.example.Active.Learning.project.account.service;


import com.example.Active.Learning.project.account.constants.DefaultValues;
import com.example.Active.Learning.project.account.exceptions.CourseNotFoundException;
import com.example.Active.Learning.project.account.exceptions.RoleNotFoundException;
import com.example.Active.Learning.project.account.exceptions.UserAlreadyRegisteredException;

import com.example.Active.Learning.project.account.exceptions.UserNotFoundException;
import com.example.Active.Learning.project.account.interfaces.IUserService;
import com.example.Active.Learning.project.account.models.*;
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

import java.time.DateTimeException;
import java.time.Instant;
import java.util.*;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @Autowired
    CourseRepository courseRepository;

    @Override
    public ResponseEntity<?> createUser(@NonNull SignUpRequest signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new UserAlreadyRegisteredException(MessageResponse.EMAIL_ALREADY_EXISTS));
        }

        User user = new User(
                signUpRequest.getUsername(),
                signUpRequest.getFirstname(),
                signUpRequest.getLastname(),
                signUpRequest.getAuthType(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getProvider(),
                signUpRequest.getAvatar(),
                signUpRequest.getLastSeen(),
                true);

        user.setRoles(addRoles());
        user.setCourse(addCourse());

        try {
            userRepository.save(user);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }

        return ResponseEntity.ok("");
    }

    public Set<Role> addRoles() {
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(DefaultValues.DEFAULT_ROLE.getName())
                .orElseThrow(() -> new RoleNotFoundException(MessageResponse.ROLE_NOT_FOUND_ERROR));
        roles.add(userRole);
        return roles;
    }

    public Course addCourse() {
        return courseRepository.findByName(DefaultValues.DEFAULT_COURSE.getName())
                .orElseThrow(() -> new CourseNotFoundException(MessageResponse.COURSE_NOT_FOUND));
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers(int pageNo, int pageSize, String sortBy, String sortDir, String searchValue) {
        Page<User> users = null;
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        if (searchValue != null) {
            users = userRepository.findAllByLike(searchValue,pageable);
        } else {
            users = userRepository.findAll(pageable);
        }
        return ResponseEntity.ok(users.stream().toList());
    }

    public String getActiveSearchValue(String searchValue){
        String searchValueToLowerCase = searchValue.toLowerCase();
        return searchValueToLowerCase == "active"?"1":searchValueToLowerCase == "inactive"?"0":null;
    }

    public ResponseEntity<?> getUserById(@NonNull Long id) {
        Optional<User> user = userRepository.findById(id);
        if (!user.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new UserNotFoundException(MessageResponse.USER_NOT_FOUND));
        }
        return ResponseEntity.ok().body(user.get());
    }

    public ResponseEntity<Optional<User>> getUserByUsername(@NonNull String username) {
        Optional<User> user = userRepository.findByUsername(username);
        User user1 = userRepository.findByUsername(username).get();
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> updateUser(@NonNull Long id, @NonNull User user) {
        User userExist = userRepository.findById(id).
                orElseThrow(() ->
                        new UserNotFoundException(MessageResponse.USER_NOT_FOUND));
//        userExist.setUsername(user.getEmail());
//        userExist.setPassword(user.getPassword());
//        userRepository.save(userExist);
        return ResponseEntity.ok(user);
    }


}
