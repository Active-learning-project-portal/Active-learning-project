package com.example.Active.Learning.project.account.service;

import com.example.Active.Learning.project.account.interfaces.IUserRoleService;
import lombok.NonNull;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public class UserRoleServiceImpl implements IUserRoleService {
    @Override
    public ResponseEntity<?> updateUserRole(@NonNull UUID roleId, @NonNull UUID userId) {
        return null;
    }
}
