package com.example.Active.Learning.project.account.repositories;

import com.example.Active.Learning.project.account.models.ECourse;
import com.example.Active.Learning.project.account.models.ERole;
import com.example.Active.Learning.project.account.models.User;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    List<User> findByIsActive(Boolean isActive);
    Boolean existsByUsername(String username);

    @Query("select u from User u where u.lastname like %:#{[0]}% or u.lastname like %:searchValue% " +
            "or u.username like %:searchValue% or u.provider like %:searchValue% " +
            "or u.firstname like %:searchValue% or u.authType like %:searchValue% " +
            " or u.isActive like %:searchValue% or u.githubUsername like %:searchValue% " )
    Page<User> findAllByLike(String searchValue, Pageable pageable);

}
