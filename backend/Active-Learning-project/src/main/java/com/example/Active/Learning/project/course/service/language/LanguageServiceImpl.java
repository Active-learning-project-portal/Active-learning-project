package com.example.Active.Learning.project.course.service.language;

import com.example.Active.Learning.project.base.repository.BaseRepository;
import com.example.Active.Learning.project.course.dto.request.language.LanguageRequest;
import com.example.Active.Learning.project.constants.MessageResponse;
import com.example.Active.Learning.project.course.dto.response.language.LanguageResponse;
import com.example.Active.Learning.project.course.interfaces.ILanguageService;
import com.example.Active.Learning.project.course.models.course.Course;
import com.example.Active.Learning.project.course.models.language.Language;
import com.example.Active.Learning.project.course.repositories.language.LanguageRepository;
import com.example.Active.Learning.project.base.service.BaseImpl;
import com.example.Active.Learning.project.course.service.course.CourseServiceImpl;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class LanguageServiceImpl extends  BaseImpl<Language,UUID> implements ILanguageService {

    @Autowired
    private final LanguageRepository languageRepository;

    @Autowired
    private  final  CourseServiceImpl courseService;

    private final ModelMapper modelMapper;

    public LanguageServiceImpl(BaseRepository<Language, UUID> baseRepository, LanguageRepository languageRepository, CourseServiceImpl courseService, ModelMapper modelMapper) {
        super(baseRepository);
        this.languageRepository = languageRepository;
        this.courseService = courseService;
        this.modelMapper = modelMapper;
    }


    private Language findLanguageById(@NonNull UUID languageId){
        return this.findById(languageId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, MessageResponse.NOT_FOUND));
    }

    @Override
    public LanguageResponse findByName(@NonNull String languageName){
        Language language = this.languageRepository.findByName(languageName)
                .orElseThrow(()->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,MessageResponse.NOT_FOUND));
        return  this.modelMapper.map(language,LanguageResponse.class);
    }

    @Override
    public void saveLanguage(@NonNull LanguageRequest languageRequest) {
       try {
           findByName(languageRequest.getName());
       }catch (ResponseStatusException e){
           Language language = this.modelMapper.map(languageRequest,Language.class);
          this.save(language);
          return;
       }catch (RuntimeException e){
           throw new RuntimeException(e.getMessage());
       }
        throw new RuntimeException(MessageResponse.LANGUAGE_ALREADY_EXISTS);
    }

    @Override
    public List<LanguageResponse> findAllLanguages() {
        return this.findAll().stream().map(language -> this.modelMapper.map(language, LanguageResponse.class)).toList();
    }

    @Override
    public void deleteById(@NonNull  UUID languageId) {
        try{
            findLanguageById(languageId);
        }catch (ResponseStatusException e){
            throw new RuntimeException(e.getMessage());
        }
        this.languageRepository.deleteById(languageId);
    }

    @Override
    public void delete(@NonNull LanguageRequest languageRequest) {
        Language languageResponse = this.languageRepository.findByName(languageRequest.getName()).orElseThrow(
                ()->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,MessageResponse.LANGUAGE_NOT_FOUND));
        this.delete(languageResponse);
    }

    @Override
    public LanguageResponse updateLanguage(@NonNull UUID languageId, @NonNull LanguageRequest languageRequest) {
        LanguageResponse languageResponse = findLanguage(languageId);
        Language language = this.modelMapper.map(languageResponse,Language.class);
        mapLanguage(language,languageRequest);
        this.update(language);
        return this.modelMapper.map(language,LanguageResponse.class);
    }

    private void mapLanguage(@NonNull  Language language,@NonNull  LanguageRequest languageRequest){
        language.setName(languageRequest.getName());
        language.setAvatar(languageRequest.getAvatar());
        Set<Course> courses = updateCourse(language,languageRequest);
        language.setCourse(courses);
    }

    //TODO Assuming that the course of language will be in order with language request, think about a way t avoid this.
    private Set<Course> updateCourse(@NonNull Language language, @NonNull LanguageRequest languageRequest){
        AtomicInteger i = new AtomicInteger();
        Set<Course> courses = language.getCourse().stream().map(
                course -> {
                    Course courseResponse = this.modelMapper.map(this.courseService.updateCourse(course.getId(),languageRequest.getCourse().stream().toList().get(i.get())), Course.class);
                    i.getAndIncrement();
                    return courseResponse;
                }).collect(Collectors.toSet());
        return courses;
    }

    @Override
    public LanguageResponse findLanguage(@NonNull UUID languageId) {
        try{
            return this.modelMapper.map(this.findLanguageById(languageId),LanguageResponse.class);
        }catch (ResponseStatusException e){
            throw new RuntimeException(MessageResponse.NOT_FOUND);
        }
    }

    public boolean existById(@NonNull UUID uuid){
        return languageRepository.existsById(uuid);
    }

}
