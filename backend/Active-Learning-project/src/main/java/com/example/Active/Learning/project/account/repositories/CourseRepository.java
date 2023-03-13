package com.example.Active.Learning.project.account.repositories;

import com.example.Active.Learning.project.account.models.course.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CourseRepository extends org.springframework.data.jpa.repository.JpaRepository<Course, java.util.UUID> {
    @Query("select u from Course u where u.name like %:#{[0]}% or u.name like %:searchValue%")
    Page<Course> findAllByLike(String searchValue, Pageable pageable);
}
