package com.example.Active.Learning.project.course.dto.response.course;

import com.example.Active.Learning.project.course.dto.response.unit.UnitResponse;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CourseResponse {
    private UUID id;
    private String name;
    private String avatar;
    private Set<UnitResponse> unit;
}
