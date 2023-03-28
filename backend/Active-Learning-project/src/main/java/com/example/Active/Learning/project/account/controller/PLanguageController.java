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
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/language")
public class PLanguageController {


    private final PLanguageServiceImpl pLanguageService;

    @Autowired
    public PLanguageController(PLanguageServiceImpl pLanguageService) {
        this.pLanguageService = pLanguageService;
    }


}
