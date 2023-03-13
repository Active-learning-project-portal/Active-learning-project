package com.example.Active.Learning.project.account.service;


import com.example.Active.Learning.project.account.constants.DefaultValues;
import com.example.Active.Learning.project.account.models.enums.EAddOrRemove;
import com.example.Active.Learning.project.account.models.enums.ERole;
import com.example.Active.Learning.project.account.models.role.Role;
import com.example.Active.Learning.project.account.models.users.User;
import com.example.Active.Learning.project.account.payload.request.UserRequest;
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

    public User saveUser(@NonNull UserRequest userRequest) {
        User user = mapUserRequestToUser(userRequest);
        user.setRoles(addRole(DefaultValues.DEFAULT_ROLE.getName()));
        try {
             this.save(user);
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage());
        }
        return user;
    }

    public User mapUserRequestToUser(UserRequest userRequest){
        return new User(
                userRequest.getUsername(),
                userRequest.getFirstname(),
                userRequest.getLastname(),
                encoder.encode(userRequest.getPassword()),
                userRequest.getProvider(),
                userRequest.getAvatar(),
                new Date());
    }
    public Set<Role> addRole(ERole eRole) {
        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName(eRole);
        roles.add(userRole);
        return roles;
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
