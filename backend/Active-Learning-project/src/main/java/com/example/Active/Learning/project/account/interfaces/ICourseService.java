package com.example.Active.Learning.project.account.interfaces;

import com.example.Active.Learning.project.account.models.Course;
import com.example.Active.Learning.project.account.models.User;
import com.example.Active.Learning.project.account.payload.request.CourseRequest;
import com.example.Active.Learning.project.account.payload.request.SignUpRequest;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICourseService {
    ResponseEntity<?> createCourse(@NonNull CourseRequest courseRequest);
    ResponseEntity<List<Course>> getAllCourse(int pageNo, int pageSize, String sortBy, String sortDir, String searchValue);
    ResponseEntity<?> getCourseById(@NonNull Long courseId);
    ResponseEntity<List<Course>> updateCourse(@NonNull Long courseId, @NonNull CourseRequest courseRequest);
    ResponseEntity<?> deleteCourse(@NonNull Long courseId);
}
