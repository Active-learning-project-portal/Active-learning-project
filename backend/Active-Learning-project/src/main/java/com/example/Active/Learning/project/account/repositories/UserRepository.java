package com.example.Active.Learning.project.account.repositories;

import com.example.Active.Learning.project.account.models.users.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public interface UserRepository extends IRepository<User> {
    Optional<User> findByUsername(String username);
    Boolean existsByUsername(String username);

    @Query("select u from User u where u.lastname like %:#{[0]}% or u.lastname like %:searchValue% " +
            "or u.username like %:searchValue% or u.provider like %:searchValue% " +
            "or u.firstname like %:searchValue% or u.dateJoined like %:searchValue% " )
    Page<User> findAllByLike(String searchValue, Pageable pageable);

}
