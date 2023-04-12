package com.example.Active.Learning.project.course.models.language;


import com.example.Active.Learning.project.base.models.BaseEntity;
import com.example.Active.Learning.project.course.models.course.Course;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "languages",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name"),
        })
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Language extends BaseEntity {
        private String name;
        private String avatar;
        @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
        private Set<Course> course = new HashSet<>();
}
