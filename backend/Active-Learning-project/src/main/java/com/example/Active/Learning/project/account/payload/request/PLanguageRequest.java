package com.example.Active.Learning.project.account.payload.request;

import com.example.Active.Learning.project.account.models.course.Course;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
public class PLanguageRequest {
    private String name;
    private  Set<Course> courses;
    private String avatar;
}
