package com.example.Active.Learning.project.account.controller;


import com.example.Active.Learning.project.account.constants.DefaultValues;
import com.example.Active.Learning.project.account.models.users.User;
import com.example.Active.Learning.project.account.payload.request.SignUpRequest;
import com.example.Active.Learning.project.account.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<List<User>> getAllUsers(
    ) {
//        return userService.getAllUsers();
        return null;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINEE') or hasRole('TRAINER') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<?> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> create(@RequestBody SignUpRequest user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('TRAINER') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<User> updateUserById(@PathVariable UUID id, @RequestBody SignUpRequest user) {
        return userService.updateUser(id,user);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('TRAINER') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID id) {
        ResponseEntity<Void> user = null;
        try{
          user = userService.deleteUser(id);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return user;
    }
}
