package com.example.Active.Learning.project.account.service;

import com.example.Active.Learning.project.account.models.role.Role;
import com.example.Active.Learning.project.account.repositories.BaseRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoleServiceImpl extends BaseImpl<Role,UUID> {


    public RoleServiceImpl(BaseRepository baseRepository) {
        super(baseRepository);
    }
}
