package com.example.Active.Learning.project.course.dto.request.unit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnitRequest {
    private String name;
    private String contentUrl;
    private String description;
}
