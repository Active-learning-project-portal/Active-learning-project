package com.example.Active.Learning.project.account.controller;


import com.example.Active.Learning.project.account.constants.DefaultValues;
import com.example.Active.Learning.project.account.models.User;
import com.example.Active.Learning.project.account.payload.request.SignUpRequest;
import com.example.Active.Learning.project.account.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINEE') or hasRole('TRAINER') or hasRole('SUPER_ADMIN')")
    public ResponseEntity<List<User>> getAllUsers(
            @RequestParam(value = "pageNo", defaultValue = DefaultValues.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = DefaultValues.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = DefaultValues.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DefaultValues.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            @RequestParam(value = "searchValue",required = false) String searchValue
    ) {
        return userService.getAllUsers(pageNo,pageSize,sortBy,sortDir,searchValue);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINEE') or hasRole('TRAINER')")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> create(@RequestBody SignUpRequest user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('TRAINER')")
    public ResponseEntity<List<User>> updateUserById(@PathVariable Long id, @RequestBody SignUpRequest user) {
        return userService.updateUser(id,user);
    }
}
