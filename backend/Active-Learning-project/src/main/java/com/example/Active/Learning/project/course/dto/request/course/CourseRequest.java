package com.example.Active.Learning.project.course.dto.request.course;

import com.example.Active.Learning.project.course.dto.request.unit.UnitRequest;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseRequest {
    private String name;
    private String avatar;
    private Set<UnitRequest> unit;
}
