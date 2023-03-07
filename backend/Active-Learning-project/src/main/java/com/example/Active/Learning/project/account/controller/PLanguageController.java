package com.example.Active.Learning.project.account.controller;


import com.example.Active.Learning.project.account.constants.DefaultValues;
import com.example.Active.Learning.project.account.models.PLanguage;
import com.example.Active.Learning.project.account.payload.request.PLanguageRequest;
import com.example.Active.Learning.project.account.service.PLanguageServiceImpl;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/language")
public class PLanguageController {


    private final PLanguageServiceImpl pLanguageService;

    @Autowired
    public PLanguageController(PLanguageServiceImpl pLanguageService) {
        this.pLanguageService = pLanguageService;
    }


    @PostMapping()
    public ResponseEntity<?> createLanguage(@RequestBody PLanguageRequest pLanguageRequest) {
        return pLanguageService.createLanguage(pLanguageRequest);
    }

    @GetMapping()
    public ResponseEntity<List<PLanguage>> getAllLanguages(
            @RequestParam(value = "pageNo", defaultValue = DefaultValues.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = DefaultValues.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = DefaultValues.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DefaultValues.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            @RequestParam(value = "searchValue", required = false) String searchValue) {
        return pLanguageService.getAllLanguages(pageNo,pageSize,sortBy,sortDir,searchValue);
    }

    @GetMapping("/{languageId}")
    public ResponseEntity<?> getLanguageById(@NonNull Long languageId) {
        return pLanguageService.getLanguageById(languageId);
    }


    @PutMapping()
    public ResponseEntity<List<PLanguage>> updateLanguage(@NonNull Long languageId, @NonNull PLanguageRequest pLanguageRequest) {
        return pLanguageService.updateLanguage(languageId,pLanguageRequest);
    }

    @DeleteMapping("/{languageId}")
    public ResponseEntity<?> deleteLanguage(@NonNull Long languageId) {
        return pLanguageService.deleteLanguage(languageId);
    }
}
