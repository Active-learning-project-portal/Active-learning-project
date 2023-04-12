package com.example.Active.Learning.project.user_course.repository;


import com.example.Active.Learning.project.base.repository.BaseRepository;
import com.example.Active.Learning.project.user_course.models.Registration;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RegistrationRepository extends BaseRepository<Registration, UUID> {
    Optional<Registration> findByUserId(UUID userId);
}
