package com.example.Active.Learning.project.course.service.course;

import com.example.Active.Learning.project.base.repository.BaseRepository;
import com.example.Active.Learning.project.course.dto.request.course.CourseRequest;
import com.example.Active.Learning.project.course.dto.response.course.CourseResponse;
import com.example.Active.Learning.project.constants.MessageResponse;
import com.example.Active.Learning.project.course.interfaces.ICourseService;
import com.example.Active.Learning.project.course.models.course.Course;
import com.example.Active.Learning.project.course.models.unit.Unit;
import com.example.Active.Learning.project.course.repositories.course.CourseRepository;
import com.example.Active.Learning.project.base.service.BaseImpl;
import com.example.Active.Learning.project.course.service.unit.UnitServiceImpl;
import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Service
public class CourseServiceImpl extends BaseImpl<Course,UUID> implements ICourseService {

    @Autowired
    private final CourseRepository courseRepository;
    @Autowired
    private final UnitServiceImpl unitService;
    @Autowired
    private final ModelMapper modelMapper;

    public CourseServiceImpl(BaseRepository<Course, UUID> baseRepository, CourseRepository courseRepository, UnitServiceImpl unitService, ModelMapper modelMapper) {
        super(baseRepository);
        this.courseRepository = courseRepository;
        this.unitService = unitService;
        this.modelMapper = modelMapper;
    }

    private Course findCourse(UUID courseId){
        return this.findById(courseId).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.NOT_FOUND, String.format(MessageResponse.USER_NOT_FOUND +" "+ courseId)));
    }



    @Override
    public void saveCourse(@NonNull CourseRequest courseRequest) {
        try {
            findByName(courseRequest.getName());
        }catch (ResponseStatusException e){
            save(this.modelMapper.map(courseRequest, Course.class));
        }catch (RuntimeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<CourseResponse> findAllCourses() {
        return this.findAll().stream().map(course -> this.modelMapper.map(course,CourseResponse.class)).toList();
    }

    @Override
    public CourseResponse findByName(@NonNull String courseName) {
        Course course = this.courseRepository.findByName(courseName)
                .orElseThrow(()->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, MessageResponse.NOT_FOUND));
        return  this.modelMapper.map(course, CourseResponse.class);
    }

    @Override
    public CourseResponse findCourseById(@NonNull UUID courseId) {
        try{
            return this.modelMapper.map(findCourse(courseId),CourseResponse.class);
        }catch (ResponseStatusException e){
            throw new RuntimeException(MessageResponse.NOT_FOUND);
        }
    }

    @Override
    public CourseResponse updateCourse(@NonNull UUID courseId, @NonNull CourseRequest courseRequest) {
        Course course = findCourse(courseId);
        mapCourse(course, courseRequest);
        return this.modelMapper.map(this.update(course), CourseResponse.class);
    }

    private void mapCourse(Course course, CourseRequest courseRequest) {
        course.setName(courseRequest.getName());
        course.setAvatar(courseRequest.getAvatar());
        Set<Unit> units = updateUnit(course,courseRequest);
        course.setUnit(units);
    }

    private Set<Unit> updateUnit(@NonNull Course course, @NonNull CourseRequest courseRequest){
        AtomicInteger i = new AtomicInteger();
        Set<Unit> units = course.getUnit().stream().map(
                unit -> {
                    Unit unit1 = this.modelMapper.map(this.unitService.updateUnit(unit.getId(),courseRequest.getUnit().stream().toList().get(i.get())), Unit.class);
                    i.getAndIncrement();
                    return unit1;
                }).collect(Collectors.toSet());
        return units;
    }

    @Override
    public void deleteById(@NonNull UUID userId) {
        try {
            this.findById(userId);
        }catch (ResponseStatusException e){
            throw new RuntimeException(e.getMessage());
        }
        this.courseRepository.deleteById(userId);
    }

    @Override
    public void delete(@NonNull CourseRequest courseRequest) {
        CourseResponse courseResponse = this.findByName(courseRequest.getName());
        if(courseResponse == null){
            throw new RuntimeException(MessageResponse.USER_NOT_FOUND);
        }
        Optional<Course> course =this.findAll().stream().filter(u
                        -> u.getName().equals(courseResponse.getName()))
                .findFirst();
        course.ifPresent(this::delete);
    }

    public boolean existById(@NonNull UUID uuid){
        return courseRepository.existsById(uuid);
    }
}
