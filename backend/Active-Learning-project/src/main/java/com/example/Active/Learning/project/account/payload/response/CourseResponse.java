package com.example.Active.Learning.project.account.payload.response;

import com.example.Active.Learning.project.account.models.PLanguage;
import com.example.Active.Learning.project.account.models.course.Course;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@Setter
@Getter
public class CourseResponse {
    private Optional<PLanguage> pLanguage;
    private Set<Course> courses;
}
