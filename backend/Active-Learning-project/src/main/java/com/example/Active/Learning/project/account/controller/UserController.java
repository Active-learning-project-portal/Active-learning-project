package com.example.Active.Learning.project.account.controller;


import com.example.Active.Learning.project.account.models.User;
import com.example.Active.Learning.project.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINEE') or hasRole('TRAINER') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINEE') or hasRole('TRAINER')")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('TRAINER')")
    public ResponseEntity<Optional<User>> getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('TRAINER')")
    public ResponseEntity<User> updateUserById(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id,user);
    }
}
