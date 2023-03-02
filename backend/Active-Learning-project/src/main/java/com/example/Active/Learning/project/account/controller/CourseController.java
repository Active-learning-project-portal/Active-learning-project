package com.example.Active.Learning.project.account.controller;

import com.example.Active.Learning.project.account.constants.DefaultValues;
import com.example.Active.Learning.project.account.interfaces.ICourseService;
import com.example.Active.Learning.project.account.models.Course;
import com.example.Active.Learning.project.account.payload.request.CourseRequest;
import com.example.Active.Learning.project.account.service.CourseServiceImpl;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/courses")
public class CourseController{

    private final CourseServiceImpl courseService;

    @Autowired
    public CourseController(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }


    @PostMapping()
    public ResponseEntity<?> createCourse(@RequestBody CourseRequest courseRequest) {
      return courseService.createCourse(courseRequest);
    }


    @GetMapping()
    public ResponseEntity<List<Course>> getAllCourse(
            @RequestParam(value = "pageNo", defaultValue = DefaultValues.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = DefaultValues.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = DefaultValues.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = DefaultValues.DEFAULT_SORT_DIRECTION, required = false) String sortDir,
            @RequestParam(value = "searchValue",required = false) String searchValue
    ) {
        return courseService.getAllCourse(pageNo,pageSize,sortBy,sortDir,searchValue);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<?> getCourseById(@PathVariable Long courseId) {
        return courseService.getCourseById(courseId);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<List<Course>> updateCourse(@PathVariable Long courseId, @RequestBody CourseRequest courseRequest) {
        return courseService.updateCourse(courseId,courseRequest);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId) {
        return courseService.deleteCourse(courseId);
    }
}
