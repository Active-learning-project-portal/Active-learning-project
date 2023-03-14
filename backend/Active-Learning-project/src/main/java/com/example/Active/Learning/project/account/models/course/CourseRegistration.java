package com.example.Active.Learning.project.account.models.course;


import com.example.Active.Learning.project.account.models.BaseEntity;
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
public class CourseRegistration extends BaseEntity {
    //TODO
    private UUID courseId;
    private  UUID userId;
    private UUID langId;
}
