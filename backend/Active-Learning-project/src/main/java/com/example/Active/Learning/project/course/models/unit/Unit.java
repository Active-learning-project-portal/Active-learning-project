package com.example.Active.Learning.project.course.models.unit;

import com.example.Active.Learning.project.base.models.BaseEntity;
import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
@Entity
@Table(name = "unit")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Unit extends BaseEntity {
    private String name;
    private String contentUrl;
    private String description;
}
