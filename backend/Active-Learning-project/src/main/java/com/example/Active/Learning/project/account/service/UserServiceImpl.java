package com.example.Active.Learning.project.account.service;

import com.example.Active.Learning.project.account.models.users.User;
import com.example.Active.Learning.project.account.payload.request.UserRequest;
import com.example.Active.Learning.project.account.payload.response.AuthResponse;
import com.example.Active.Learning.project.account.payload.response.MessageResponse;
import com.example.Active.Learning.project.account.payload.response.UserResponse;
import com.example.Active.Learning.project.account.repositories.*;

import lombok.NonNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl extends BaseImpl<User,UUID>{

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository userRepository;

    ModelMapper modelMapper = new ModelMapper();

    public UserServiceImpl(BaseRepository<User, UUID> baseRepository) {
        super(baseRepository);
    }

    public UserResponse saveUser(@NonNull UserRequest userRequest) {
        User user = this.modelMapper.map(userRequest,User.class);
        try {
             this.save(user);
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage());
        }
        return mapUserToUserResponse(user);
    }


    public User findByUsername(String username){
     Optional<User> user = this.userRepository.findByUsername(username);
     if(user.isPresent()){
         return user.get();
     }
     throw new RuntimeException(MessageResponse.USER_NOT_FOUND);
    }

    public UserResponse mapUserToUserResponse(User user){
        AuthResponse authResponse = new AuthResponse(user.getToken(),user.getTokenType());
        UserResponse userResponse =  this.modelMapper.map(user,UserResponse.class);
        userResponse.setAuthResponse(authResponse);
        return userResponse;
    }

    public boolean userExistByUsername(@NonNull  String username){
        return userRepository.existsByUsername(username);
    }

    public User updateUser(@NonNull UUID userId, @NonNull UserRequest userRequest) {
        User user = userRepository.findById(userId).
                orElseThrow();
          user.setAvatar(userRequest.getAvatar());
          user.setFirstname(userRequest.getFirstname());
          user.setLastname(userRequest.getLastname());
          user.setPassword(userRequest.getPassword());
          user.setProvider(userRequest.getProvider());
          user.setLastname(userRequest.getLastname());
          try{
             return this.updateById(user,user.getId());
          }catch (Exception e){
              throw  new RuntimeException(e.getMessage());
          }
    }

}
