package com.example.Active.Learning.project.account.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "courses",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name"),
        })
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    @JoinTable(name = "course_units",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "unit_id"))
    private Set<Units> units = new HashSet<>();

    @Column(length = 20)
    private  String name;
    public Course(String name, Set<Units> units) {
        this.name = name;
        this.units = units;
    }

    public Course(String name) {
        this.name = name;
    }
}
