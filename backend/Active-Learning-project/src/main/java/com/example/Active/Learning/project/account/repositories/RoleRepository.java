package com.example.Active.Learning.project.account.repositories;

import com.example.Active.Learning.project.account.models.ERole;
import com.example.Active.Learning.project.account.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findByName(ERole name);
}
