package com.example.Active.Learning.project.course.interfaces;

import com.example.Active.Learning.project.course.dto.request.language.LanguageRequest;
import com.example.Active.Learning.project.course.dto.response.language.LanguageResponse;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

public interface ILanguageService {
    void saveLanguage(@NonNull LanguageRequest languageRequest);
    List<LanguageResponse> findAllLanguages();
    void deleteById(@NonNull UUID languageId);
    void delete(@NonNull LanguageRequest languageRequest);
    LanguageResponse updateLanguage(@NonNull UUID languageId, @NonNull  LanguageRequest languageRequest);
    LanguageResponse findLanguage(@NonNull UUID languageId);
    LanguageResponse findByName(@NonNull String languageName);
}
