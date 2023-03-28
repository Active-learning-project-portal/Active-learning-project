package com.example.Active.Learning.project.account.models;


import com.example.Active.Learning.project.account.models.course.Course;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "language",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name"),
        })
@NoArgsConstructor
public class PLanguage extends BaseEntity {
        @Id
        @GeneratedValue(generator = "uuid2")
        @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
        @Column(name = "id")
        private UUID id;
        private String name;
        private String avatar;
        @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
        @JoinTable(name = "lang_course",
                joinColumns = {@JoinColumn(name = "lang_id",referencedColumnName = "id")},
                inverseJoinColumns = {@JoinColumn(name = "course_id",referencedColumnName = "id")})
        private Set<Course> courses = new HashSet<>();

        public PLanguage(String name,Set<Course> courses,String avatar) {
                this.name = name;
                this.courses = courses;
                this.avatar = avatar;
        }
}
