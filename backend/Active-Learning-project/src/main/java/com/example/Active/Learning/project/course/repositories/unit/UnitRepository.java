package com.example.Active.Learning.project.course.repositories.unit;

import com.example.Active.Learning.project.course.models.unit.Unit;
import com.example.Active.Learning.project.base.repository.BaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface UnitRepository extends BaseRepository<Unit, UUID> {
    Optional<Unit> findByName(String unitName);

}
