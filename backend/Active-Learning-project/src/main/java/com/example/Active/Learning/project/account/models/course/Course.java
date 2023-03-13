package com.example.Active.Learning.project.account.models.course;


import com.example.Active.Learning.project.account.models.BaseEntity;
import com.example.Active.Learning.project.account.models.PLanguage;
import com.example.Active.Learning.project.account.models.units.Unit;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "course")
@NoArgsConstructor
public class Course extends BaseEntity {
    private  String name;

    private  String avatar;

    @ManyToMany(mappedBy = "courses",cascade = CascadeType.ALL)
    private Set<PLanguage> pLanguage;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "course_unit",
            joinColumns = {@JoinColumn(name = "course_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "unit_id",referencedColumnName = "id")})
    private Set<Unit> units;

    public Course(String name) {
        this.name = name;
    }
}
