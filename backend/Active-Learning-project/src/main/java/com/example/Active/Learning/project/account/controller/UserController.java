package com.example.Active.Learning.project.account.controller;


import com.example.Active.Learning.project.account.models.users.User;
import com.example.Active.Learning.project.account.payload.request.UserRequest;
import com.example.Active.Learning.project.account.payload.response.Message;
import com.example.Active.Learning.project.account.payload.response.MessageResponse;
import com.example.Active.Learning.project.account.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
      return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINEE') or hasRole('TRAINER') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) {
        Optional<User> user = userService.findById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PostMapping("/signup")
    public ResponseEntity<?> create(@RequestBody UserRequest userRequest) {
        boolean  userExist = userService.userExistByUsername(userRequest.getUsername());
        if(userExist){
            return ResponseEntity.badRequest().body(MessageResponse.EMAIL_ALREADY_EXISTS);
        }
        return ResponseEntity.ok(userService.saveUser(userRequest));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('TRAINER') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<User> updateUserById(@PathVariable UUID id, @RequestBody UserRequest userRequest) {
        User updateUser = null;
        try{
            updateUser = userService.updateUser(id, userRequest);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        if(updateUser == null) return ResponseEntity.badRequest().body(null);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('TRAINER') or hasRole('SUPER_ADMIN')")
    public void deleteUserById(@PathVariable UUID id) {
        try{
          userService.deleteById(id);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
