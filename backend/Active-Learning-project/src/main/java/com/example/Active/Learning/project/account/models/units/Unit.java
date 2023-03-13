package com.example.Active.Learning.project.account.models.units;

import com.example.Active.Learning.project.account.models.BaseEntity;
import com.example.Active.Learning.project.account.models.course.Course;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;
import java.util.UUID;


@Setter
@Getter
@Entity
@Table(name = "unit")
@NoArgsConstructor
public class Unit extends BaseEntity {
    private String name;
    private String contentUrl;
    private String description;

    @ManyToMany(mappedBy = "units")
    private Set<Course> course;

    public Unit(UUID id, String name, String contentUrl,String description) {
        this.name = name;
        this.contentUrl = contentUrl;
        this.description = description;
    }
}
