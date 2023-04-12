package com.example.Active.Learning.project.user_course.models;

import com.example.Active.Learning.project.base.models.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "registration")
public class Registration extends BaseEntity {
    private UUID courseId;
    private UUID userId;
    private UUID languageId;
}
