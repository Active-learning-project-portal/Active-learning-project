package com.example.Active.Learning.project.account.payload.request;

import com.example.Active.Learning.project.account.models.Units;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CourseRequest {

    private String name;
    private Set<Units> units;
}
