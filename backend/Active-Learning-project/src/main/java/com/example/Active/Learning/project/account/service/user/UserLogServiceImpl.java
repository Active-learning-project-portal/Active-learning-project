package com.example.Active.Learning.project.account.service.user;

import com.example.Active.Learning.project.account.dto.request.user.UserLogRequest;
import com.example.Active.Learning.project.base.repository.BaseRepository;
import com.example.Active.Learning.project.constants.MessageResponse;
import com.example.Active.Learning.project.account.dto.response.user.UserLogResponse;
import com.example.Active.Learning.project.account.dto.response.user.UserResponse;
import com.example.Active.Learning.project.account.interfaces.IUserLogService;
import com.example.Active.Learning.project.account.models.enums.ELoginType;
import com.example.Active.Learning.project.account.models.enums.EUserState;
import com.example.Active.Learning.project.account.models.users.User;
import com.example.Active.Learning.project.account.models.users.UserLog;
import com.example.Active.Learning.project.account.repositories.UserLogRepository;
import com.example.Active.Learning.project.base.service.BaseImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserLogServiceImpl extends BaseImpl<UserLog, UUID> implements IUserLogService {

    @Autowired
    private final UserLogRepository userLogRepository;
    @Autowired
    private final UserServiceImpl userService;
    private final ModelMapper modelMapper;

    public UserLogServiceImpl(BaseRepository<UserLog, UUID> baseRepository, UserLogRepository userLogRepository, UserServiceImpl userService, ModelMapper modelMapper) {
        super(baseRepository);
        this.userLogRepository = userLogRepository;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    private UserLog findUserLog(@NonNull UUID logId){
        return this.findById(logId)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,MessageResponse.NOT_FOUND));
    }

    private UserResponse findUser(@NonNull UserLogRequest userLogRequest){
        UserResponse userResponse = userService.findByUsername(userLogRequest.getUserRequest().getUsername());
        Optional<User> user = userService.findById(userResponse.getId());
        if(user.isEmpty()){
            throw new RuntimeException(MessageResponse.USER_NOT_FOUND);
        }
        return userResponse;
    }

    private void mapUserLog(@NonNull UserLog userLog,@NonNull UserLogRequest userLogRequest){
        userLog.setUserState(userLogRequest.getEUserState());
        userLog.setLoginType(userLogRequest.getELoginType());
    }

    @Override
    public UserLogResponse saveUserLog(@NonNull UserLogRequest userLogRequest) {
        UserLog userLog = this.modelMapper.map(userLogRequest,UserLog.class);
        try {
             updateLog(findByUser(userLogRequest).getId(),userLogRequest);
        }catch (ResponseStatusException e){
            UserResponse userResponse = findUser(userLogRequest);
            Optional<User> user = this.userService.findById(userResponse.getId());
            user.ifPresent(userLog::setUser);
            this.save(userLog);
        }catch (RuntimeException e){
            throw  new RuntimeException(e.getMessage());
        }
        return this.modelMapper.map(userLog,UserLogResponse.class);
    }

    @Override
    public List<UserLogResponse> findAllLogs() {
        return this.findAll().stream().map(
                userLog ->
                        this.modelMapper.map(userLog,UserLogResponse.class)).toList();
    }

    @Override
    public List<UserLogResponse> findByLoginType(@NonNull ELoginType loginType) {
        List<UserLog> userLogResponses = this.userLogRepository.findByLoginType(loginType);
        return userLogResponses.stream().map(
                userLogResponse ->
                        this.modelMapper.map(userLogResponse,UserLogResponse.class)).toList();
    }

    @Override
    public List<UserLogResponse> findByUserState(@NonNull EUserState userState) {
        List<UserLog> userLogResponses = this.userLogRepository.findByUserState(userState);
        return userLogResponses.stream().map(
                userLogResponse ->
                        this.modelMapper.map(userLogResponse,UserLogResponse.class)).toList();
    }

    @Override
    public UserLogResponse findByUser(@NonNull UserLogRequest userLogRequest) {
        UserResponse userResponse = findUser(userLogRequest);
        Optional<UserLog> userLog = this.userLogRepository.findAll()
                .stream().filter(log->
                        log.getUser().getUsername().equals(userResponse.getUsername())).findFirst();
        if(userLog.isEmpty()){
            log.error("Could not find logs for user"+userResponse);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return this.modelMapper.map(userLog.get(),UserLogResponse.class);
    }

    @Override
    public UserLogResponse findUserLogById(@NonNull UUID logId) {
        try{
            return this.modelMapper.map(findUserLog(logId),UserLogResponse.class);
        }catch (ResponseStatusException e){
            throw new RuntimeException(MessageResponse.NOT_FOUND);
        }
    }

    @Override
    public UserLogResponse updateLog(@NonNull UUID logId, @NonNull UserLogRequest userLogRequest) {
        UserLog userLog =findUserLog(logId);
        mapUserLog(userLog,userLogRequest);
        return this.modelMapper.map(this.update(userLog),UserLogResponse.class);
    }

    @Override
    public void deleteById(@NonNull UUID logId) {
       this.delete(findUserLog(logId));
    }

    @Override
    public void delete(@NonNull UserLogRequest userLogRequest) {
        UserLogResponse userLogResponse = findByUser(userLogRequest);
        UserLog mapUserLog = this.modelMapper.map(userLogResponse,UserLog.class);
        this.delete(mapUserLog);
    }
}
