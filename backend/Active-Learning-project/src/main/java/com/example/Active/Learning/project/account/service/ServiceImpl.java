package com.example.Active.Learning.project.account.service;

import com.example.Active.Learning.project.account.interfaces.IService;
import com.example.Active.Learning.project.account.repositories.IRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ServiceImpl<T> implements IService<T> {


    @Autowired
    private final IRepository<T> repository;


    public ServiceImpl(IRepository<T> repository) {
        this.repository = repository;
    }

    @Override
    public List<T> findAll() {
       // return repository.findAll();
        return null;
    }

    @Override
    public ResponseEntity<T> findById(@NonNull UUID id) {
        Optional<T> entity = repository.findById(id);
        return entity.map(t -> ResponseEntity.ok().body(t)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public void create(@NonNull T entity) {
        T object = repository.save(entity);
        ResponseEntity.ok().body(object);
    }

    @Override
    public ResponseEntity<T> update(@NonNull T entity, @NonNull UUID id) {
        Optional<T> object = repository.findById(id);
        if(object.isPresent()){
            repository.save(entity);
            return ResponseEntity.ok().body(object.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public ResponseEntity<Void> delete(@NonNull UUID id) {
      T object  = repository.findById(id).orElse(null);
        if (object == null) {
            return ResponseEntity.notFound().build();
        }
        repository.delete(object);
        return ResponseEntity.ok().build();
    }
}
