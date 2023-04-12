package com.example.Active.Learning.project.account.repositories;

import com.example.Active.Learning.project.account.models.enums.ELoginType;
import com.example.Active.Learning.project.account.models.enums.EUserState;
import com.example.Active.Learning.project.account.models.users.UserLog;
import com.example.Active.Learning.project.base.repository.BaseRepository;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

public interface UserLogRepository extends BaseRepository<UserLog, UUID> {
    List<UserLog> findByLoginType(@NonNull ELoginType loginType);
    List<UserLog> findByUserState(@NonNull EUserState userState);
}
