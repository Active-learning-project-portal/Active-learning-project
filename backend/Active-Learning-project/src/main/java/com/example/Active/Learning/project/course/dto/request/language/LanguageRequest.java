package com.example.Active.Learning.project.course.dto.request.language;

import com.example.Active.Learning.project.course.dto.request.course.CourseRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LanguageRequest {
    private String name;
    private String avatar;
    private Set<CourseRequest> course;
}
