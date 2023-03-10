package com.example.Active.Learning.project.account.repositories;

import com.example.Active.Learning.project.account.models.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public interface UserRepository extends IRepository<User> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);

    Page<User> findAllContaining(String searchValue, Pageable pageable);

}
