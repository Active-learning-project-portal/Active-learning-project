package com.example.Active.Learning.project.account.payload.request;

import com.example.Active.Learning.project.account.models.course.Course;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;


@Getter
@Setter
public class PLanguageRequest {
    private UUID  languageId;
    private String name;
    private  Set<Course> courses;
    private String avatar;

    public PLanguageRequest(UUID languageId, String name, Set<Course> courses, String avatar) {
        this.languageId = languageId;
        this.name = name;
        this.courses = courses;
        this.avatar = avatar;
    }
}
