package com.example.Active.Learning.project.account.interfaces;

import lombok.NonNull;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface IService<T> {
    ResponseEntity<List<T>> findAll(int pageNo, int pageSize, String sortBy, String sortDir, String searchValue);
    ResponseEntity<T> findById(@NonNull UUID id);
    ResponseEntity<T> create(@NonNull T entity);
    ResponseEntity<T> update(@NonNull T entity,@NonNull UUID id);
    ResponseEntity<Void> delete(@NonNull UUID id);
}
