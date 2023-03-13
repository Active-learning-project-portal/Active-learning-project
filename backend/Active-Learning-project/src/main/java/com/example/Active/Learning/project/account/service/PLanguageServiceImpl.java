package com.example.Active.Learning.project.account.service;

import com.example.Active.Learning.project.account.exceptions.CourseNotFoundException;
import com.example.Active.Learning.project.account.interfaces.IPLanguageService;
import com.example.Active.Learning.project.account.models.PLanguage;
import com.example.Active.Learning.project.account.payload.request.PLanguageRequest;
import com.example.Active.Learning.project.account.payload.response.MessageResponse;
import com.example.Active.Learning.project.account.repositories.PLanguageRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PLanguageServiceImpl implements IPLanguageService {

    private final PLanguageRepository pLanguageRepository;

    @Autowired
    public PLanguageServiceImpl(PLanguageRepository pLanguageRepository) {
        this.pLanguageRepository = pLanguageRepository;
    }

    @Override
    public ResponseEntity<?> createLanguage(@NonNull PLanguageRequest pLanguageRequest) {

        if (pLanguageRequest.getName() == null ||
                pLanguageRequest.getAvatar() == null) {
            return ResponseEntity
                    .badRequest()
                    .body(List.of(new CourseNotFoundException(MessageResponse.NO_LANGUAGE_ADDED).getMessage()));
        }

        if (pLanguageRepository.existsByName(pLanguageRequest.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(List.of(new CourseNotFoundException(MessageResponse.LANGUAGE_ALREADY_EXISTS).getMessage()));
        }

        PLanguage pLanguage = new PLanguage(
                pLanguageRequest.getName(),
                pLanguageRequest.getCourses(),
                pLanguageRequest.getAvatar());

        try {
            pLanguageRepository.save(pLanguage);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }

        return ResponseEntity.ok(List.of(pLanguage));
    }

    @Override
    public ResponseEntity<List<PLanguage>> getAllLanguages(int pageNo, int pageSize, String sortBy, String sortDir, String searchValue) {
        Page<PLanguage> languages = null;
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        if (searchValue != null) {
            languages = pLanguageRepository.findAllByLike(searchValue, pageable);
        } else {
            languages = pLanguageRepository.findAll(pageable);
        }
        return ResponseEntity.ok(languages.stream().toList());
    }

    @Override
    public ResponseEntity<?> getLanguageById(@NonNull UUID languageId) {
        Optional<PLanguage> course = pLanguageRepository.findById(languageId);
        if (!course.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new CourseNotFoundException(MessageResponse.USER_NOT_FOUND));
        }
        return ResponseEntity.ok().body(course.get());
    }

    @Override
    public ResponseEntity<List<PLanguage>> updateLanguage(@NonNull UUID languageId, @NonNull PLanguageRequest pLanguageRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteLanguage(@NonNull UUID languageId) {
        Optional<PLanguage> language = pLanguageRepository.findById(languageId);
        if (!language.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(List.of(new CourseNotFoundException(MessageResponse.LANGUAGE_NOT_FOUND)));
        }

        try {
            pLanguageRepository.deleteById(languageId);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
        return ResponseEntity.ok(List.of(language));
    }
}
