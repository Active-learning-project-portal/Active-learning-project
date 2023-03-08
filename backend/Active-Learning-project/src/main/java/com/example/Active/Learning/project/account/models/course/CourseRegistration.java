package com.example.Active.Learning.project.account.models.course;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "courses_registration")
public class CourseRegistration {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id")
    private UUID id;
    @NonNull
    private UUID courseId;
    @NonNull
    private  UUID userId;
    @NonNull
    private UUID langId;
}
