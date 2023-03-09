package com.example.Active.Learning.project.account.service;

import com.example.Active.Learning.project.account.interfaces.IService;
import com.example.Active.Learning.project.account.repositories.IRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<List<T>> findAll(int pageNo, int pageSize, String sortBy, String sortDir, String searchValue) {
        Page<T> pageEntity = null;
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        if (searchValue != null) {
            pageEntity = repository.findAllContaining(searchValue,pageable);
        } else {
            pageEntity = repository.findAll(pageable);
        }
        return ResponseEntity.ok(pageEntity.stream().toList());
    }

    @Override
    public ResponseEntity<T> findById(@NonNull UUID id) {
        Optional<T> entity = repository.findById(id);
        return entity.map(t -> ResponseEntity.ok().body(t)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<T> create(@NonNull T entity) {
        T object = repository.save(entity);
        return ResponseEntity.ok().body(object);
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
