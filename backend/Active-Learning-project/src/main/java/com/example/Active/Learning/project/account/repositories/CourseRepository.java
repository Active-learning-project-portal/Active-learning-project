package com.example.Active.Learning.project.account.repositories;

import com.example.Active.Learning.project.account.models.course.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;


@Repository
public interface CourseRepository extends JpaRepository<Course, UUID> {
    Optional<Course> findByName(String name);

    @Query("select u from Course u where u.name like %:#{[0]}% or u.name like %:searchValue%")
    Page<Course> findAllByLike(String searchValue, Pageable pageable);

    Boolean existsByName(String name);
}
