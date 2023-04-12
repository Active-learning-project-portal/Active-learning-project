package com.example.Active.Learning.project.base.repository;

import com.example.Active.Learning.project.base.models.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BaseRepository <T extends BaseEntity,id extends UUID> extends JpaRepository<T, id> {
}
