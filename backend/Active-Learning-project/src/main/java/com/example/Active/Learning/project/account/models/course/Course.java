package com.example.Active.Learning.project.account.models.course;


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
public class Course {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;
    @NonNull
    private  String name;

    @NonNull
    private  String avatar;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "course_unit",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "unit_id"))
    private Set<Unit> units = new HashSet<>();

    public Course(String name) {
        this.name = name;
    }
}
