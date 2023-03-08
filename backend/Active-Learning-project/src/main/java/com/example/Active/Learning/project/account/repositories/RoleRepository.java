package com.example.Active.Learning.project.account.repositories;

import com.example.Active.Learning.project.account.models.enums.ERole;
import com.example.Active.Learning.project.account.models.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findByName(ERole name);
}
