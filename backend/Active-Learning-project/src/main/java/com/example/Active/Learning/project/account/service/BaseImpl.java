package com.example.Active.Learning.project.account.service;

import com.example.Active.Learning.project.account.exceptions.UserNotFoundException;
import com.example.Active.Learning.project.account.exceptions.model.ErrorMessage;
import com.example.Active.Learning.project.account.interfaces.IBase;
import com.example.Active.Learning.project.account.models.BaseEntity;
import com.example.Active.Learning.project.account.models.users.User;
import com.example.Active.Learning.project.account.payload.response.MessageResponse;
import com.example.Active.Learning.project.account.repositories.BaseRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
public  class BaseImpl<T extends BaseEntity,id extends UUID> implements IBase<T,id> {

    private final BaseRepository<T, id> baseRepository;

    @Autowired
    public BaseImpl(BaseRepository<T, id> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public T save(T entity) {

        return (T) baseRepository.save(entity);
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
        T t = baseRepository.findById((id)entity.getId()).
                orElseThrow();
        return (T) baseRepository.save(t);
    }

    @Override
    public T updateById(T entity, id entityId) {
        Optional<T> optional = baseRepository.findById(entityId);
        if(optional.isPresent()){
            return (T) baseRepository.save(entity);
        }
       return null;
    }

    @Override
    public T delete(T entity) {
        Optional<T> optional = baseRepository.findById((id) entity.getId());
        if(optional.isPresent()){
            baseRepository.delete(entity);
            return  entity;
        }
        return null;
    }

    @Override
    public Optional<T> deleteById(id entityId) {
        Optional<T> optional = baseRepository.findById(entityId);
        if(optional.isPresent()){
           baseRepository.deleteById(entityId);
           return  optional;
        }
        return Optional.empty();
    }
}
