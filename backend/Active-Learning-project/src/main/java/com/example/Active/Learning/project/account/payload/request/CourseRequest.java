package com.example.Active.Learning.project.account.payload.request;

import com.example.Active.Learning.project.account.models.PLanguage;
import com.example.Active.Learning.project.account.models.course.Course;
import com.example.Active.Learning.project.account.models.users.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Getter
@Setter
public class CourseRequest {
    private User user;
    private PLanguage languages;
    private Set<Course> courses;
    private  String name;
    private  String avatar;
}
