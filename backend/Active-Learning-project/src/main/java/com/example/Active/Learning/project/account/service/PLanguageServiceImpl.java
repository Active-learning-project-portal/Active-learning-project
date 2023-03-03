package com.example.Active.Learning.project.account.service;

import com.example.Active.Learning.project.account.exceptions.CourseNotFoundException;
import com.example.Active.Learning.project.account.interfaces.IPLanguageService;
import com.example.Active.Learning.project.account.models.Course;
import com.example.Active.Learning.project.account.models.PLanguage;
import com.example.Active.Learning.project.account.payload.request.PLanguageRequest;
import com.example.Active.Learning.project.account.payload.response.MessageResponse;
import com.example.Active.Learning.project.account.repositories.PLanguageRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PLanguageServiceImpl implements IPLanguageService {

    private  final PLanguageRepository pLanguageRepository;

    @Autowired
    public PLanguageServiceImpl(PLanguageRepository pLanguageRepository) {
        this.pLanguageRepository = pLanguageRepository;
    }

    @Override
    public ResponseEntity<?> createLanguage(@NonNull PLanguageRequest pLanguageRequest) {
        if (pLanguageRepository.existsByName(pLanguageRequest.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new CourseNotFoundException(MessageResponse.LANGUAGE_ALREADY_EXISTS));
        }

        PLanguage pLanguage = new PLanguage(
                pLanguageRequest.getName(),
                pLanguageRequest.getCourse(),
                pLanguageRequest.getThumbNail());

        try {
            pLanguageRepository.save(pLanguage);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }

        return ResponseEntity.ok("");
    }

    @Override
    public ResponseEntity<List<PLanguage>> getAllLanguages(int pageNo, int pageSize, String sortBy, String sortDir, String searchValue) {
        return null;
    }

    @Override
    public ResponseEntity<?> getLanguageById(@NonNull Long languageId) {
        return null;
    }

    @Override
    public ResponseEntity<List<PLanguage>> updateLanguage(@NonNull Long languageId, @NonNull PLanguageRequest pLanguageRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteLanguage(@NonNull Long languageId) {
        return null;
    }
}
