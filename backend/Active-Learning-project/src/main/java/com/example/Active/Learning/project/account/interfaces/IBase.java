package com.example.Active.Learning.project.account.interfaces;

import com.example.Active.Learning.project.account.models.BaseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IBase<T extends BaseEntity,id extends UUID>{
    public abstract T save(T entity);
    public abstract List<T> findAll();
    public abstract Optional<T> findById(id entityId);
    public abstract T update(T entity);
    public abstract T updateById(T entity, id entityId);
    public abstract T delete(T entity);
    public abstract Optional<T> deleteById(id entityId);
}
