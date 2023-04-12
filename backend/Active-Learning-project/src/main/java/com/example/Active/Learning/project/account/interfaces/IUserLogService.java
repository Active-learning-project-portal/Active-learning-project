package com.example.Active.Learning.project.account.interfaces;

import com.example.Active.Learning.project.account.dto.request.user.UserLogRequest;
import com.example.Active.Learning.project.account.dto.response.user.UserLogResponse;
import com.example.Active.Learning.project.account.models.enums.ELoginType;
import com.example.Active.Learning.project.account.models.enums.EUserState;
import lombok.NonNull;

import java.util.List;
import java.util.UUID;

public interface IUserLogService {
    UserLogResponse saveUserLog(@NonNull UserLogRequest userLogRequest);
    List<UserLogResponse> findAllLogs();
    List<UserLogResponse>  findByLoginType(@NonNull  ELoginType loginType);
    List<UserLogResponse> findByUserState(@NonNull  EUserState userState);
    UserLogResponse  findByUser(@NonNull UserLogRequest userLogRequest);
    UserLogResponse findUserLogById(@NonNull UUID logId);
    UserLogResponse updateLog(@NonNull UUID logId, @NonNull UserLogRequest userLogRequest);
    void deleteById(@NonNull UUID logId);
    void delete(@NonNull UserLogRequest userLogRequest);
}
