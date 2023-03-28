package com.example.Active.Learning.project.account.repositories;

import com.example.Active.Learning.project.account.models.users.User;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public interface UserRepository extends BaseRepository<User,UUID>{
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);

}
