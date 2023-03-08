package com.example.Active.Learning.project.account.interfaces;

import com.example.Active.Learning.project.account.models.PLanguage;
import com.example.Active.Learning.project.account.payload.request.PLanguageRequest;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface IPLanguageService {

    ResponseEntity<?> createLanguage(@NonNull PLanguageRequest pLanguageRequest);
    ResponseEntity<List<PLanguage>> getAllLanguages(int pageNo, int pageSize, String sortBy, String sortDir, String searchValue);
    ResponseEntity<?> getLanguageById(@NonNull UUID languageId);
    ResponseEntity<List<PLanguage>> updateLanguage(@NonNull UUID languageId, @NonNull PLanguageRequest pLanguageRequest);
    ResponseEntity<?> deleteLanguage(@NonNull UUID languageId);
}
