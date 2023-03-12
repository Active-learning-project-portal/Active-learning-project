package com.example.Active.Learning.project.account.repositories;

import com.example.Active.Learning.project.account.models.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BaseRepository <T extends BaseEntity,id extends UUID> extends JpaRepository<T, id> {
}
