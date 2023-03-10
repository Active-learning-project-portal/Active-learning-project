package com.example.Active.Learning.project.account.interfaces;

import lombok.NonNull;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface IService<T> {
    List<T> findAll();
    ResponseEntity<T> findById(@NonNull UUID id);
    void create(@NonNull T entity);
    ResponseEntity<T> update(@NonNull T entity,@NonNull UUID id);
    ResponseEntity<Void> delete(@NonNull UUID id);
}
