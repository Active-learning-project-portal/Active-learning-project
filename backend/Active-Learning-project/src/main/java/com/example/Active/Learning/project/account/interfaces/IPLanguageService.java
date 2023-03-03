package com.example.Active.Learning.project.account.interfaces;

import com.example.Active.Learning.project.account.models.PLanguage;
import com.example.Active.Learning.project.account.payload.request.PLanguageRequest;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPLanguageService {

    ResponseEntity<?> createLanguage(@NonNull PLanguageRequest pLanguageRequest);
    ResponseEntity<List<PLanguage>> getAllLanguages(int pageNo, int pageSize, String sortBy, String sortDir, String searchValue);
    ResponseEntity<?> getLanguageById(@NonNull Long languageId);
    ResponseEntity<List<PLanguage>> updateLanguage(@NonNull Long languageId, @NonNull PLanguageRequest pLanguageRequest);
    ResponseEntity<?> deleteLanguage(@NonNull Long languageId);
}
