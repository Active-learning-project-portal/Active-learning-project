package com.example.Active.Learning.project.account.service;

import com.example.Active.Learning.project.account.exceptions.CourseNotFoundException;
import com.example.Active.Learning.project.account.interfaces.IPLanguageService;
import com.example.Active.Learning.project.account.models.PLanguage;
import com.example.Active.Learning.project.account.payload.request.PLanguageRequest;
import com.example.Active.Learning.project.account.payload.response.MessageResponse;
import com.example.Active.Learning.project.account.repositories.BaseRepository;
import com.example.Active.Learning.project.account.repositories.PLanguageRepository;
import lombok.NonNull;
import org.hibernate.validator.cfg.defs.UUIDDef;
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
public class PLanguageServiceImpl extends BaseImpl {

    @Autowired
    private PLanguageRepository pLanguageRepository;

    @Autowired
    public PLanguageServiceImpl(BaseRepository<PLanguage, UUID> baseRepository) {
        super(baseRepository);
    }

    public Optional<PLanguage> createLanguage(@NonNull PLanguageRequest pLanguageRequest) {

        Optional<PLanguage> pLanguage = this.findById(pLanguageRequest.getLanguageId());
        if(pLanguage.isEmpty()){
            PLanguage pLanguage1 = new PLanguage(
                    pLanguageRequest.getName(),
                    pLanguageRequest.getCourses(),
                    pLanguageRequest.getAvatar());
            try {
                this.save(pLanguage1);
            } catch (Exception e) {
                throw  new RuntimeException(e.getMessage());
            }

        }
        throw  new RuntimeException(MessageResponse.LANGUAGE_ALREADY_EXISTS);
    }

    public Optional<PLanguage> getLanguageById(@NonNull UUID languageId) {
        Optional<PLanguage> course = pLanguageRepository.findById(languageId);
        if (course.isEmpty()) {
            throw new RuntimeException(new CourseNotFoundException(MessageResponse.LANGUAGE_NOT_FOUND));
        }
        return course;
    }


    public Optional<PLanguage> deleteLanguageById(@NonNull UUID languageId) {
        Optional<PLanguage> language = pLanguageRepository.findById(languageId);
        if (language.isEmpty()) {
            throw new RuntimeException(new CourseNotFoundException(MessageResponse.LANGUAGE_NOT_FOUND));
        }
        try {
            this.deleteById(languageId);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return language;
    }
}
