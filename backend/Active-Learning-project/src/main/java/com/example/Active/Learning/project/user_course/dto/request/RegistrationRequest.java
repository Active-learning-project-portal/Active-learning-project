package com.example.Active.Learning.project.user_course.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class RegistrationRequest {
    private UUID courseId;
    private UUID userId;
    private UUID languageId;
}
