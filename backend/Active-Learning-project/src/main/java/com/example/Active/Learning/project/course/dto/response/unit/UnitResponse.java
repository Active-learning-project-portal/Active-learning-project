package com.example.Active.Learning.project.course.dto.response.unit;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UnitResponse {
    private UUID id;
    private String name;
    private String contentUrl;
    private String description;
}
