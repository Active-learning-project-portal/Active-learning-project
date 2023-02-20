package com.example.Active.Learning.project.account.service;


import com.example.Active.Learning.project.account.exceptions.CourseNotFoundException;
import com.example.Active.Learning.project.account.exceptions.RoleNotFoundException;
import com.example.Active.Learning.project.account.exceptions.UserAlreadyRegisteredException;

import com.example.Active.Learning.project.account.exceptions.UserNotFoundException;
import com.example.Active.Learning.project.account.models.*;
import com.example.Active.Learning.project.account.payload.request.SignInRequest;
import com.example.Active.Learning.project.account.payload.request.SignUpRequest;
import com.example.Active.Learning.project.account.payload.response.JwtResponse;
import com.example.Active.Learning.project.account.payload.response.MessageResponse;
import com.example.Active.Learning.project.account.repositories.CourseRepository;
import com.example.Active.Learning.project.account.repositories.RoleRepository;
import com.example.Active.Learning.project.account.repositories.UserRepository;
import com.example.Active.Learning.project.account.security.jwt.JwtUtils;
import com.example.Active.Learning.project.account.security.services.UserDetailsImpl;

import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;


    @Autowired
    CourseRepository courseRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    public ResponseEntity<?> saveUser(@NonNull SignUpRequest signUpRequest) {

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
        return ResponseEntity.ok(MessageResponse.USER_CREATED_SUCCESSFULLY);
    }

    public Set<Role> addRoles() {
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_TRAINEE)
                .orElseThrow(() -> new RoleNotFoundException(MessageResponse.ROLE_NOT_FOUND_ERROR));
        roles.add(userRole);
        return roles;
    }

    public Course addCourse() {
        return courseRepository.findByName(ECourse.NO_COURSE)
                .orElseThrow(() -> new CourseNotFoundException(MessageResponse.COURSE_NOT_FOUND));

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

    public ResponseEntity<?> authenticateUser(@NonNull SignInRequest signInRequest) {

        Authentication authentication = getAuthentication(signInRequest.getUsername(), signInRequest.getPassword());

        if (authentication == null) {
            return ResponseEntity
                    .badRequest()
                    .body(new UserNotFoundException(MessageResponse.USER_NOT_FOUND));
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles));
    }

    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    public ResponseEntity<List<User>> getAllActiveUsers() {
        List<User> users = userRepository.findByIsActive(true);
        return ResponseEntity.ok(users);
    }
    public ResponseEntity<List<User>> getAllInActiveUsers() {
        List<User> users = userRepository.findByIsActive(false);
        return ResponseEntity.ok(users);
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
