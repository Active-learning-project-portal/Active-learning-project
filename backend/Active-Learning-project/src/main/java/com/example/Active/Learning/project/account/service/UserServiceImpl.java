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

    public UserServiceImpl(BaseRepository<User, UUID> baseRepository) {
        super(baseRepository);
    }


    public User saveUser(@NonNull UserRequest userRequest) {
        User user = new User(
                userRequest.getUsername(),
                userRequest.getFirstname(),
                userRequest.getLastname(),
                encoder.encode(userRequest.getPassword()),
                userRequest.getProvider(),
                userRequest.getAvatar(),
                new Date(),changeRolesOnRoleSet(userRequest.getRoles(), DefaultValues.DEFAULT_ROLE.getName(),EAddOrRemove.ADD));

        try {
             this.save(user);
        } catch (Exception e) {
           throw new RuntimeException(e.getMessage());
        }
        return user;
    }

    private User saveRolesToUser(@NonNull UUID id,@NonNull UserRequest userRequest,@NonNull Set<Role> rolesToAdd){
          userRequest.setRoles(rolesToAdd);
        return this.updateUser(id,userRequest);
    }

    private Set<Role>  changeRolesOnRoleSet(Set<Role> currentRoles,@NonNull  ERole eRole ,@NonNull EAddOrRemove method){
       if(currentRoles == null){
           currentRoles = new HashSet<>();
           currentRoles.add(new Role(ERole.ROLE_TRAINEE));
           return currentRoles;
       }
        switch (method){
         case ADD -> currentRoles.add(new Role(eRole));
         case REMOVE -> currentRoles.remove(new Role(eRole));
         default -> currentRoles.add(new Role(ERole.ROLE_TRAINEE));
     }
     return currentRoles;
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
