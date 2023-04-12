package com.example.Active.Learning.project.course.repositories.course;

import com.example.Active.Learning.project.course.models.course.Course;
import com.example.Active.Learning.project.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface CourseRepository extends BaseRepository<Course, UUID> {
    Optional<Course> findByName(String courseName);
    boolean existsById(UUID id);

}
