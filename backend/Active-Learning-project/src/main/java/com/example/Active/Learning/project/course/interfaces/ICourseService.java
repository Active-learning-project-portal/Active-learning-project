package com.example.Active.Learning.project.course.interfaces;

import com.example.Active.Learning.project.course.dto.request.course.CourseRequest;
import com.example.Active.Learning.project.course.dto.response.course.CourseResponse;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

public interface ICourseService {
    void saveCourse(@NonNull CourseRequest courseRequest);
    List<CourseResponse> findAllCourses();
    CourseResponse findByName(@NonNull String courseName);
    CourseResponse findCourseById(@NonNull UUID courseId);
    CourseResponse updateCourse(@NonNull UUID courseId, @NonNull CourseRequest courseRequest);
    void deleteById(@NonNull UUID userId);
    void delete(@NonNull CourseRequest courseRequest);
}
