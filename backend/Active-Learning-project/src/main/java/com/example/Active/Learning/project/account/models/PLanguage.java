package com.example.Active.Learning.project.account.models;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
public class PLanguage {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id", nullable = false)
        private Long id;

        private String name;

        private String thumbNail;
        @ManyToMany(fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
        @JoinTable(name = "language_courses",
                joinColumns = @JoinColumn(name = "language_id"),
                inverseJoinColumns = @JoinColumn(name = "course_id"))
        private Set<Course> course= new HashSet<>();

        public PLanguage(String name, Set<Course> course,String thumbNail) {
                this.name = name;
                this.course = course;
                this.thumbNail = thumbNail;
        }
}
