package com.example.Active.Learning.project.account.service;


import com.example.Active.Learning.project.account.exceptions.CourseNotFoundException;
import com.example.Active.Learning.project.account.interfaces.ICourseService;
import com.example.Active.Learning.project.account.models.Course;
import com.example.Active.Learning.project.account.payload.request.CourseRequest;
import com.example.Active.Learning.project.account.payload.response.MessageResponse;
import com.example.Active.Learning.project.account.repositories.CourseRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements ICourseService {


    @Autowired
    private CourseRepository courseRepository;

    @Override
    public ResponseEntity<?> createCourse(@NonNull CourseRequest courseRequest) {
        if (courseRepository.existsByName(courseRequest.getName())) {
            return ResponseEntity
                    .badRequest()
                    .body(new CourseNotFoundException(MessageResponse.COURSE_ALREADY_EXISTS));
        }

        Course course = new Course(
                courseRequest.getName(),
                courseRequest.getUnits());

        try {
            courseRepository.save(course);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }

        return ResponseEntity.ok("");
    }

    @Override
    public ResponseEntity<List<Course>> getAllCourse(int pageNo, int pageSize, String sortBy, String sortDir, String searchValue) {
        Page<Course> courses = null;
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        if (searchValue != null) {
            courses = courseRepository.findAllByLike(searchValue, pageable);
        } else {
            courses = courseRepository.findAll(pageable);
        }
        return ResponseEntity.ok(courses.stream().toList());
    }

    @Override
    public ResponseEntity<?> getCourseById(@NonNull Long courseId) {
        Optional<Course> course = courseRepository.findById(courseId);
        if (!course.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new CourseNotFoundException(MessageResponse.USER_NOT_FOUND));
        }
        return ResponseEntity.ok().body(course.get());
    }

    @Override
    public ResponseEntity<List<Course>> updateCourse(@NonNull Long courseId, @NonNull CourseRequest courseRequest) {
        return null;
    }

    @Override
    public ResponseEntity<?> deleteCourse(@NonNull Long courseId) {

        Optional<Course> course = courseRepository.findById(courseId);
        if (!course.isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body(new CourseNotFoundException(MessageResponse.COURSE_NOT_FOUND));
        }

        try {
            courseRepository.deleteById(courseId);
        } catch (Exception e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
        return ResponseEntity.ok("");
    }
}
