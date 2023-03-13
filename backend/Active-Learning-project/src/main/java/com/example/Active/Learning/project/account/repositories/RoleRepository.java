package com.example.Active.Learning.project.account.repositories;

import com.example.Active.Learning.project.account.models.enums.ERole;
import com.example.Active.Learning.project.account.models.role.Role;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends BaseRepository<Role,UUID> {
    Boolean existsByName(String username);

    Role findByName(ERole name);
}
