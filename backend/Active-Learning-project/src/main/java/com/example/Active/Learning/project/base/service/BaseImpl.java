package com.example.Active.Learning.project.base.service;

import com.example.Active.Learning.project.base.interfaces.IBase;
import com.example.Active.Learning.project.base.models.BaseEntity;
import com.example.Active.Learning.project.base.repository.BaseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public  class BaseImpl<T extends BaseEntity,id extends UUID> implements IBase<T,id> {

    private final BaseRepository<T, id> baseRepository;

    @Override
    public void save(T entity) {
        entity.setCreatedOn(new Date());
        entity.setModifiedOn(new Date());
        baseRepository.save(entity);
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll();
    }

    @Override
    public Optional<T> findById(id entityId) {
        return baseRepository.findById(entityId);
    }

    @Override
    public T update(T entity) {
        entity.setModifiedOn(new Date());
        return (T) baseRepository.save(entity);
    }

    @Override
    public void delete(T entity) {
        baseRepository.delete(entity);
    }
}
