package com.example.Active.Learning.project.user_course.service;


import com.example.Active.Learning.project.account.service.user.UserServiceImpl;
import com.example.Active.Learning.project.base.repository.BaseRepository;
import com.example.Active.Learning.project.base.service.BaseImpl;
import com.example.Active.Learning.project.constants.MessageResponse;
import com.example.Active.Learning.project.course.service.course.CourseServiceImpl;
import com.example.Active.Learning.project.course.service.language.LanguageServiceImpl;
import com.example.Active.Learning.project.user_course.dto.request.RegistrationRequest;
import com.example.Active.Learning.project.user_course.dto.response.RegistrationResponse;
import com.example.Active.Learning.project.user_course.interfaces.IRegistrationService;
import com.example.Active.Learning.project.user_course.models.Registration;
import com.example.Active.Learning.project.user_course.repository.RegistrationRepository;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RegistrationServiceImpl extends BaseImpl<Registration, UUID>  implements IRegistrationService {

    @Autowired
    private final CourseServiceImpl courseService;

    @Autowired
    private final UserServiceImpl userService;

    @Autowired
    private final LanguageServiceImpl languageService;

    @Autowired
    private final RegistrationRepository registrationRepository;


    private final ModelMapper modelMapper;

    public RegistrationServiceImpl(BaseRepository<Registration, UUID> baseRepository, CourseServiceImpl courseService, UserServiceImpl userService, LanguageServiceImpl languageService, RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        super(baseRepository);
        this.courseService = courseService;
        this.userService = userService;
        this.languageService = languageService;
        this.registrationRepository = registrationRepository;
        this.modelMapper = modelMapper;
    }

    private List<Registration> findList(){
        return this.findAll().stream().toList();
    }

    @Override
    public List<RegistrationResponse> findAllRegistration() {
        List<RegistrationResponse> registrationResponses = new ArrayList<>();
         List<Registration> registrationRequests = findList();
         registrationRequests.forEach(registration -> {
             RegistrationResponse registrationResponse = new RegistrationResponse();
             registrationResponse.setId(registration.getId());
             registrationResponse.setUser(userService.findUserById(registration.getUserId()));
             registrationResponse.setCourse(courseService.findCourseById(registration.getCourseId()));
             registrationResponse.setLanguage(languageService.findLanguage(registration.getLanguageId()));
             registrationResponses.add(registrationResponse);
         });
        return registrationResponses;
    }

    @Override
    public void saveRegistration(@NonNull RegistrationRequest registrationRequest) {
        Registration registration = this.modelMapper.map(registrationRequest,Registration.class);
        try{
            checkRegistrationIds(registration);
            this.save(registration);
        } catch (RuntimeException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
        }
    }

    private void checkRegistrationIds(@NonNull Registration registration){
        UUID userId = registration.getUserId();
        UUID courseId = registration.getCourseId();
        UUID languageId =registration.getLanguageId();
        if(!userService.existById(userId)){
            throw new RuntimeException(MessageResponse.USER_NOT_FOUND);
        }
        if(!courseService.existById(courseId)){
            throw new RuntimeException(MessageResponse.COURSE_NOT_FOUND);
        }
        if(!languageService.existById(languageId)){
            throw new RuntimeException(MessageResponse.LANGUAGE_NOT_FOUND);
        }
        if(userAlreadyRegistered(registration.getUserId())){
            throw new RuntimeException(MessageResponse.USER_ALREADY_REGISTERED);
        }
    }

    @Override
    public RegistrationResponse findRegistrationById(@NonNull UUID registrationId) {
        return this.modelMapper.map(registrationRepository.findById(registrationId),RegistrationResponse.class);
    }

    private boolean userAlreadyRegistered(@NonNull UUID userId){
       return registrationRepository.findByUserId(userId).isPresent();
     }

    @Override
    public RegistrationResponse updateRegistration(@NonNull UUID registrationId, @NonNull RegistrationRequest registrationRequest) {
        Optional<Registration> registration = registrationRepository.findById(registrationId);
        if(registration.isPresent()){
            mapRegistration(registration.get(),registrationRequest);
            this.update(registration.get());
        }
        throw new RuntimeException(MessageResponse.NO_REGISTRATION_FOUND);
    }

    public void mapRegistration(Registration registration,RegistrationRequest registrationRequest){
        registration.setCourseId(registrationRequest.getCourseId());
        registration.setUserId(registrationRequest.getUserId());
        registration.setLanguageId(registrationRequest.getLanguageId());
    }

    @Override
    public void deleteById(@NonNull UUID registrationID) {
        Optional<Registration> registration = registrationRepository.findById(registrationID);
        if(registration.isPresent()){
            this.delete(registration.get());
            return;
        }
        throw new RuntimeException(MessageResponse.NO_REGISTRATION_FOUND);
    }

    @Override
    public void delete(@NonNull RegistrationRequest registrationRequest) {
        Registration registration = this.modelMapper.map(registrationRequest,Registration.class);
        this.delete(registration);
    }
}
