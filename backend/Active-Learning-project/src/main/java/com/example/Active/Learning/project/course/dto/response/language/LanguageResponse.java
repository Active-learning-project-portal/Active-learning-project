package com.example.Active.Learning.project.course.dto.response.language;

import com.example.Active.Learning.project.course.dto.response.course.CourseResponse;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LanguageResponse {
    private UUID id;
    private String name;
    private String avatar;
    private Set<CourseResponse> course;
}
