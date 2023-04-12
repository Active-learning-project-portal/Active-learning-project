package com.example.Active.Learning.project.course.repositories.language;

import com.example.Active.Learning.project.course.models.language.Language;
import com.example.Active.Learning.project.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface LanguageRepository extends BaseRepository<Language,UUID> {

    Optional<Language> findByName(String languageName);
    boolean existsById(UUID id);
}
