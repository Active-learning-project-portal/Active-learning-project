package com.example.Active.Learning.project.course.models.course;


import com.example.Active.Learning.project.base.models.BaseEntity;
import com.example.Active.Learning.project.course.models.unit.Unit;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Course extends BaseEntity {
    private String name;
    private String avatar;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Unit> unit = new HashSet<>();
}

