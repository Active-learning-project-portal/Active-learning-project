package com.example.Active.Learning.project.user_course.interfaces;

import com.example.Active.Learning.project.user_course.dto.request.RegistrationRequest;
import com.example.Active.Learning.project.user_course.dto.response.RegistrationResponse;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

public interface IRegistrationService {
    List<RegistrationResponse> findAllRegistration();
    void saveRegistration(@NonNull RegistrationRequest registrationRequest);

    RegistrationResponse findRegistrationById(@NonNull UUID registrationId);
    RegistrationResponse updateRegistration(@NonNull UUID registrationId,@NonNull RegistrationRequest registrationRequest);
    void  deleteById(@NonNull UUID registrationID);
    void  delete(@NonNull RegistrationRequest registrationRequest);
}
