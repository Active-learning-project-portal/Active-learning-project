package com.example.Active.Learning.project.account.repositories;

import com.example.Active.Learning.project.account.models.Course;
import com.example.Active.Learning.project.account.models.ECourse;
import com.example.Active.Learning.project.account.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    Optional<Course> findByName(ECourse name);
}
