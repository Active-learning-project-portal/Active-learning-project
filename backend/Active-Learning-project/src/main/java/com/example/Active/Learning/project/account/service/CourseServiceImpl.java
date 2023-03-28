package com.example.Active.Learning.project.account.service;

import com.example.Active.Learning.project.account.models.PLanguage;
import com.example.Active.Learning.project.account.models.course.Course;
import com.example.Active.Learning.project.account.models.users.User;
import com.example.Active.Learning.project.account.payload.request.CourseRequest;
import com.example.Active.Learning.project.account.payload.request.PLanguageRequest;
import com.example.Active.Learning.project.account.payload.request.UserRequest;
import com.example.Active.Learning.project.account.payload.response.CourseResponse;
import com.example.Active.Learning.project.account.payload.response.MessageResponse;
import com.example.Active.Learning.project.account.repositories.BaseRepository;
import com.example.Active.Learning.project.account.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;


@Service
public class CourseServiceImpl extends BaseImpl{

    @Autowired
    PLanguageServiceImpl pLanguageService;

    @Autowired
    UnitServiceImpl unitService;

    @Autowired
    CourseRepository courseRepository;

    public CourseServiceImpl(BaseRepository<Course, UUID> baseRepository) {
        super(baseRepository);
    }
    
    public CourseResponse saveCourse(CourseRequest courseRequest) {
        Optional<PLanguage> language = this.pLanguageService.getLanguageById(courseRequest.getLanguages().getId());
        CourseResponse courseResponse = null;
        if(language.isEmpty()){
            Optional<PLanguage> pLanguageRequest = bindLanguageFromRequest(courseRequest,language);
            courseResponse.setPLanguage(pLanguageRequest);
        }
        throw new RuntimeException(MessageResponse.COURSE_NOT_FOUND);
    }

 public Optional<PLanguage> bindLanguageFromRequest(CourseRequest courseRequest,Optional<PLanguage>  pLanguage){
     PLanguageRequest pLanguageRequest = new PLanguageRequest(
            pLanguage.get().getId(),
             pLanguage.get().getName(),
             pLanguage.get().getCourses(),
             pLanguage.get().getAvatar()
     );
     return  this.pLanguageService.createLanguage(pLanguageRequest);
 }
}
