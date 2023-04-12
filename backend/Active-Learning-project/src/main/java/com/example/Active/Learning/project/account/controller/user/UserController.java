package com.example.Active.Learning.project.account.controller.user;


import com.example.Active.Learning.project.constants.EndPoints;
import com.example.Active.Learning.project.constants.MessageResponse;
import com.example.Active.Learning.project.account.dto.response.user.UserResponse;
import com.example.Active.Learning.project.account.dto.request.user.UserRequest;
import com.example.Active.Learning.project.account.service.user.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(EndPoints.USERS)
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER') or hasRole('SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<UserResponse>> getAllUsers(
    ) {
      return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINEE') or hasRole('TRAINER') or hasRole('SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> getUser(@PathVariable UUID id) {
        try {
            UserResponse userResponse = userService.findUserById(id);
            return ResponseEntity.accepted().body(userResponse);
        }catch (ResponseStatusException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody UserRequest userRequest) {
        if(userRequest.getUsername() == null || userRequest.getPassword() == null){
            return ResponseEntity.badRequest().body(MessageResponse.USER_NAME_OR_PASSWORD_DOES_NOT_EXIST);
        }
        try{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.saveUser(userRequest));
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('TRAINER') or hasRole('TRAINEE')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> updateUser(@PathVariable UUID id, @RequestBody UserRequest userRequest) {
        try {
            UserResponse updateUser = userService.updateUser(id, userRequest);
            return ResponseEntity.accepted().body(updateUser);
        }catch (Exception e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> deleteUserById(@PathVariable UUID id) {
        try{
          userService.deleteById(id);
          return ResponseEntity.accepted().body(MessageResponse.USER_DELETED);
        }catch (Exception e) {
           return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> deleteUser(@RequestBody UserRequest userRequest) {
        try{
            userService.delete(userRequest);
            return ResponseEntity.accepted().body(MessageResponse.USER_DELETED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
