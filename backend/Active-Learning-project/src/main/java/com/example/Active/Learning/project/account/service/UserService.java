package com.example.Active.Learning.project.account.service;


import com.example.Active.Learning.project.account.exceptions.RoleNotFoundException;
import com.example.Active.Learning.project.account.exceptions.UserAlreadyRegisteredException;

import com.example.Active.Learning.project.account.exceptions.UserNotFoundException;
import com.example.Active.Learning.project.account.models.ERole;
import com.example.Active.Learning.project.account.models.Role;
import com.example.Active.Learning.project.account.models.User;
import com.example.Active.Learning.project.account.payload.request.SignInRequest;
import com.example.Active.Learning.project.account.payload.request.SignUpRequest;
import com.example.Active.Learning.project.account.payload.response.JwtResponse;
import com.example.Active.Learning.project.account.payload.response.MessageResponse;
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
    private AuthenticationManager authenticationManager;

    MessageResponse messageResponse = new MessageResponse();
    @Autowired
    private JwtUtils jwtUtils;

    public ResponseEntity<?> saveUser(@NonNull SignUpRequest signUpRequest) {

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new UserAlreadyRegisteredException(messageResponse.USERNAME_ALREADY_EXISTS));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new UserAlreadyRegisteredException(messageResponse.EMAIL_ALREADY_EXISTS));
        }

        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getProvider(),
                signUpRequest.getPictureUrl());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.TRAINEE)
                    .orElseThrow(() -> new RoleNotFoundException(messageResponse.ROLE_NOT_FOUND_ERROR));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "SUPER_ADMIN":
                        Role superAdminRole = roleRepository.findByName(ERole.SUPER_ADMIN)
                                .orElseThrow(() -> new RoleNotFoundException(messageResponse.ROLE_NOT_FOUND_ERROR));
                        roles.add(superAdminRole);
                        break;
                    case "ADMIN":
                        Role adminRole = roleRepository.findByName(ERole.ADMIN)
                                .orElseThrow(() -> new RoleNotFoundException(messageResponse.ROLE_NOT_FOUND_ERROR));
                        roles.add(adminRole);
                        break;
                    case "TRAINER":
                        Role trainerRole = roleRepository.findByName(ERole.TRAINER)
                                .orElseThrow(() -> new RoleNotFoundException(messageResponse.ROLE_NOT_FOUND_ERROR));
                        roles.add(trainerRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.TRAINEE)
                                .orElseThrow(() -> new RoleNotFoundException(messageResponse.ROLE_NOT_FOUND_ERROR));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);

        try {
            userRepository.save(user);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
        return ResponseEntity.ok(messageResponse.USER_CREATED_SUCCESSFULLY);
    }

    public ResponseEntity<?> authenticateUser(@NonNull SignInRequest signInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getUsername(),
                        signInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users =userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    public ResponseEntity<Optional<User>> getUserById(@NonNull Long id) {
        Optional<User> user = userRepository.findById(id);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<Optional<User>> getUserByUsername(@NonNull String username) {
        Optional<User> user = userRepository.findByUsername(username);
        return ResponseEntity.ok(user);
    }
    public ResponseEntity<Optional<User>> getUserByEmail(@NonNull String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    public ResponseEntity<User> updateUser(@NonNull Long id, @NonNull User user) {
        User userExist = userRepository.findById(id).
                orElseThrow(()->
                        new UserNotFoundException(MessageResponse.USER_NOT_FOUND));

        userExist.setUsername(user.getUsername());
        userExist.setEmail(user.getEmail());
        userExist.setPassword(user.getPassword());
        userRepository.save(userExist);
        return ResponseEntity.ok(user);
    }
}
