package com.example.Active.Learning.project.account.controller;


import com.example.Active.Learning.project.account.payload.request.CourseRequest;
import com.example.Active.Learning.project.account.payload.response.CourseResponse;
import com.example.Active.Learning.project.account.service.CourseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/courses")
public class CourseController{

    @Autowired
    CourseServiceImpl courseService;

    public CourseController(CourseServiceImpl courseService) {
        this.courseService = courseService;
    }

    @PostMapping()
    public CourseResponse createCourse(@RequestBody CourseRequest courseRequest){
        return  courseService.saveCourse(courseRequest);
    }

    @GetMapping()
    public  CourseResponse getAllCourses(){
        return (CourseResponse) courseService.findAll();
    }
}
