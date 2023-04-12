package com.example.Active.Learning.project.course.controller.language;


import com.example.Active.Learning.project.constants.EndPoints;
import com.example.Active.Learning.project.course.dto.request.language.LanguageRequest;
import com.example.Active.Learning.project.constants.MessageResponse;
import com.example.Active.Learning.project.course.dto.response.language.LanguageResponse;
import com.example.Active.Learning.project.course.service.language.LanguageServiceImpl;
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
@RequestMapping(EndPoints.LANGUAGES)
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageServiceImpl languageService;

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER') or hasRole('SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<LanguageResponse>> getAllLanguages(
    ) {
        return ResponseEntity.ok(languageService.findAllLanguages());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINEE') or hasRole('TRAINER') or hasRole('SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> getLanguage(@PathVariable UUID id) {
        try {
            return ResponseEntity.accepted().body(languageService.findLanguage(id));
        }catch (ResponseStatusException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createLanguage(@RequestBody LanguageRequest languageRequest) {
        try {
            languageService.saveLanguage(languageRequest);
            return ResponseEntity.accepted().body(MessageResponse.LANGUAGE_CREATED_SUCCESSFUL);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<?> updateLanguage(@PathVariable UUID id, @RequestBody LanguageRequest languageRequest) {
        try {
            LanguageResponse updatedLanguage= languageService.updateLanguage(id,languageRequest);
            return ResponseEntity.accepted().body(updatedLanguage);
        }catch (Exception e){
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> deleteLanguageById(@PathVariable UUID id) {
        try{
            languageService.deleteById(id);
            return ResponseEntity.accepted().body(MessageResponse.LANGUAGE_DELETED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> deleteLanguage(@RequestBody LanguageRequest languageRequest) {
        try{
            languageService.delete(languageRequest);
            return ResponseEntity.accepted().body(MessageResponse.LANGUAGE_DELETED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
