package com.example.Active.Learning.project.user_course.dto.response;


import com.example.Active.Learning.project.account.dto.response.user.UserResponse;
import com.example.Active.Learning.project.course.dto.response.course.CourseResponse;
import com.example.Active.Learning.project.course.dto.response.language.LanguageResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationResponse {
    private UUID id;
    private CourseResponse course;
    private UserResponse user;
    private LanguageResponse language;
}
