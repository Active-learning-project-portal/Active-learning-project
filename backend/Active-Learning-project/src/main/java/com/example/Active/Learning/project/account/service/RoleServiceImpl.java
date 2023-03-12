package com.example.Active.Learning.project.account.service;

import com.example.Active.Learning.project.account.models.BaseEntity;
import com.example.Active.Learning.project.account.models.role.Role;
import com.example.Active.Learning.project.account.repositories.BaseRepository;

import java.util.UUID;

public class RoleServiceImpl extends BaseImpl<Role,UUID> {

    public RoleServiceImpl(BaseRepository baseRepository) {
        super(baseRepository);
    }
}
