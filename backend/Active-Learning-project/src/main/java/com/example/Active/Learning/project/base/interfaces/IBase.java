package com.example.Active.Learning.project.base.interfaces;

import com.example.Active.Learning.project.base.models.BaseEntity;
import lombok.NonNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IBase<T extends BaseEntity,id extends UUID>{
    public abstract void save(@NonNull T entity);
    public abstract List<T> findAll();
    public abstract Optional<T> findById(@NonNull id entityId);
    public abstract T update(@NonNull T entity);
    public abstract void delete(@NonNull T entity);
}
