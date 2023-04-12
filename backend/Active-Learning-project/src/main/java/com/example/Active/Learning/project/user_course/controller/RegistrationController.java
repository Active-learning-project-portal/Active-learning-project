package com.example.Active.Learning.project.user_course.controller;


import com.example.Active.Learning.project.constants.EndPoints;
import com.example.Active.Learning.project.constants.MessageResponse;
import com.example.Active.Learning.project.user_course.dto.request.RegistrationRequest;
import com.example.Active.Learning.project.user_course.dto.response.RegistrationResponse;
import com.example.Active.Learning.project.user_course.service.RegistrationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*",maxAge = 3600)
@RequestMapping(EndPoints.REGISTRATION)
@RequiredArgsConstructor
public class RegistrationController {

    @Autowired
    private final RegistrationServiceImpl registrationService;

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER') or hasRole('SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<RegistrationResponse>> getAllRegistrations(
    ) {
        return ResponseEntity.ok(registrationService.findAllRegistration());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINEE') or hasRole('TRAINER') or hasRole('SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> getRegistration(@PathVariable UUID id) {
        try {
            RegistrationResponse registrationResponse = registrationService.findRegistrationById(id);
            return ResponseEntity.accepted().body(registrationResponse);
        }catch (ResponseStatusException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('TRAINER') or hasRole('TRAINEE')")
    public ResponseEntity<?> createRegistration(@RequestBody RegistrationRequest registrationRequest) {
        try{
            registrationService.saveRegistration(registrationRequest);
            return ResponseEntity.ok(MessageResponse.REGISTRATION_CREATED);
        }catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN') or hasRole('TRAINER') or hasRole('TRAINEE')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> updateRegistration(@PathVariable UUID id, @RequestBody RegistrationRequest registrationRequest) {
        try {
            RegistrationResponse updateRegistration = registrationService.updateRegistration(id, registrationRequest);
            return ResponseEntity.accepted().body(updateRegistration);
        }catch (Exception e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> deleteRegistrationById(@PathVariable UUID id) {
        try{
            registrationService.deleteById(id);
            return ResponseEntity.accepted().body(MessageResponse.REGISTRATION_DELETED);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> deleteRegistration(@RequestBody RegistrationRequest registrationRequest) {
        try{
            registrationService.delete(registrationRequest);
            return ResponseEntity.accepted().body(MessageResponse.REGISTRATION_DELETED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
