package com.example.Active.Learning.project.account.service;


import com.example.Active.Learning.project.account.constants.DefaultValues;
import com.example.Active.Learning.project.account.models.enums.EAddOrRemove;
import com.example.Active.Learning.project.account.models.enums.ERole;
import com.example.Active.Learning.project.account.models.role.Role;
import com.example.Active.Learning.project.account.models.users.User;
import com.example.Active.Learning.project.account.payload.request.UserRequest;
import com.example.Active.Learning.project.account.payload.response.AuthResponse;
import com.example.Active.Learning.project.account.payload.response.MessageResponse;
import com.example.Active.Learning.project.account.payload.response.UserResponse;
import com.example.Active.Learning.project.account.repositories.*;

import lombok.NonNull;
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

    @Autowired
    RoleRepository roleRepository;

    public UserServiceImpl(BaseRepository<User, UUID> baseRepository) {
        super(baseRepository);
    }

    public UserResponse saveUser(@NonNull UserRequest userRequest) {
        User user = mapUserRequestToUser(userRequest);
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
        return new UserResponse(user.getFirstname(),user.getLastname(),user.getUsername(),user.getAvatar(),user.getProvider(),user.getRoles(),user.getDateJoined(),authResponse);
    }


    public User mapUserRequestToUser(UserRequest userRequest){
        return new User(
                userRequest.getUsername(),
                userRequest.getFirstname(),
                userRequest.getLastname(),
                encoder.encode(userRequest.getPassword()),
                userRequest.getProvider(),
                userRequest.getAvatar(),
                userRequest.getRoles(),
                new Date());
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
          user.setRoles(userRequest.getRoles());
          try{
             return this.updateById(user,user.getId());
          }catch (Exception e){
              throw  new RuntimeException(e.getMessage());
          }
    }

}
