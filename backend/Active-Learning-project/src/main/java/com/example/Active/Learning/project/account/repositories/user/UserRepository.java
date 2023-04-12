package com.example.Active.Learning.project.account.repositories.user;

import com.example.Active.Learning.project.account.models.users.User;
import com.example.Active.Learning.project.base.repository.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public interface UserRepository extends BaseRepository<User,UUID> {
    Optional<User> findByUsername(String username);
    public boolean existsById(UUID id);
}
