package com.example.Active.Learning.project.account.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IRepository<T> extends JpaRepository<T, UUID> {
    boolean existsByName(String name);
    Optional<T> findByName(String name);

    @Query("select (count(t) > 0) from T t")
    boolean existsByUsername();

    Page<T> findAllContaining(String searchValue, Pageable pageable);
}
