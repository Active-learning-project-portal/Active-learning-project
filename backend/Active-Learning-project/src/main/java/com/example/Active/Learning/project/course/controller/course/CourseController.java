package com.example.Active.Learning.project.course.controller.course;

import com.example.Active.Learning.project.constants.EndPoints;
import com.example.Active.Learning.project.course.dto.request.course.CourseRequest;
import com.example.Active.Learning.project.course.dto.response.course.CourseResponse;
import com.example.Active.Learning.project.constants.MessageResponse;
import com.example.Active.Learning.project.course.service.course.CourseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(EndPoints.COURSES)
@RequiredArgsConstructor
public class CourseController {

    private final CourseServiceImpl courseService;

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINER') or hasRole('SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<List<CourseResponse>> getAllCourses(
    ) {
        return ResponseEntity.ok(courseService.findAllCourses());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TRAINEE') or hasRole('TRAINER') or hasRole('SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<CourseResponse> getCourse(@PathVariable UUID id) {
        return ResponseEntity.accepted().body(courseService.findCourseById(id));
    }

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCourse(@RequestBody CourseRequest courseRequest) {
        courseService.saveCourse(courseRequest);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable UUID id, @RequestBody CourseRequest courseRequest) {
        CourseResponse updatedCourse= courseService.updateCourse(id,courseRequest);
        return ResponseEntity.accepted().body(updatedCourse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> deleteCourseById(@PathVariable UUID id) {
        courseService.deleteById(id);
        return ResponseEntity.accepted().body(MessageResponse.COURSE_DELETED);
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('ADMIN') or hasRole('SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<String> deleteCourse(@RequestBody  CourseRequest courseRequest) {
        courseService.delete(courseRequest);
        return ResponseEntity.accepted().body(MessageResponse.COURSE_DELETED);
    }
}
