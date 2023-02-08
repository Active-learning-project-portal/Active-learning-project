package com.example.Active.Learning.project.account.repositories;

import com.example.Active.Learning.project.account.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
