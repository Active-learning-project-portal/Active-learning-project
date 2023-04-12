package com.example.Active.Learning.project.account.service.user;

import com.example.Active.Learning.project.account.dto.request.user.UserRequest;
import com.example.Active.Learning.project.account.dto.response.auth.AuthResponse;
import com.example.Active.Learning.project.account.service.auth.AuthenticationServiceImpl;
import com.example.Active.Learning.project.base.repository.BaseRepository;
import com.example.Active.Learning.project.constants.MessageResponse;
import com.example.Active.Learning.project.account.dto.response.user.UserResponse;
import com.example.Active.Learning.project.account.interfaces.IUserService;
import com.example.Active.Learning.project.account.models.users.User;
import com.example.Active.Learning.project.account.repositories.user.UserRepository;
import com.example.Active.Learning.project.base.service.BaseImpl;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public class UserServiceImpl extends BaseImpl<User,UUID> implements IUserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private final AuthenticationServiceImpl authenticationService;

    private final ModelMapper modelMapper;

    public UserServiceImpl(BaseRepository<User, UUID> baseRepository, UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationServiceImpl authenticationService, ModelMapper modelMapper) {
        super(baseRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationService = authenticationService;
        this.modelMapper = modelMapper;
    }

    private void setPasswordEncoder(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    //Be aware of using this function, this function is passing user by reference
    private void mapUser(@NonNull User user,@NonNull  UserRequest userRequest){
        user.setRoles(userRequest.getRoles());
        user.setAvatar(userRequest.getAvatar());
        user.setLastname(userRequest.getLastname());
        user.setFirstname(userRequest.getFirstname());
        user.setProvider(userRequest.getProvider());
        setPasswordEncoder(user);
    }


    public UserResponse getLoggedInUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository
                .findByUsername(email)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"Current user could not be found"));
        return  this.modelMapper.map(user,UserResponse.class);
    }

    private User findUser(@NonNull UUID userId){
        return this.findById(userId).orElseThrow(() ->
                new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, String.format(MessageResponse.USER_NOT_FOUND)));
    }

    @Override
    public AuthResponse saveUser(@NonNull UserRequest userRequest) {
        User user = this.modelMapper.map(userRequest, User.class);
        try {
            findByUsername(user.getUsername());
        }catch (ResponseStatusException e){
            setPasswordEncoder(user);
            User userToRegister =
                    User.builder()
                            .username(userRequest.getUsername())
                            .password(userRequest.getPassword())
                            .build();
            Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
            Set<ConstraintViolation<User>> constraintViolations = validator.validate(userToRegister);

            if (!constraintViolations.isEmpty()) {
                throw new ConstraintViolationException(constraintViolations);
            }
            this.save(user);
            return authenticationService.authenticate(userRequest);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        throw new RuntimeException(MessageResponse.USER_ALREADY_EXIST);
    }

    @Override
    public List<UserResponse> findAllUsers() {
        return this.findAll().stream().map(user -> this.modelMapper.map(user,UserResponse.class)).toList();
    }

    @Override
    public UserResponse findByUsername(@NonNull String username) {
        User user =this.userRepository.findByUsername(username)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.BAD_REQUEST, String.format(MessageResponse.USER_NOT_FOUND)));
        return this.modelMapper.map(user,UserResponse.class);
    }

    @Override
    public UserResponse findUserById(@NonNull UUID userId) {
        try{
            return this.modelMapper.map(findUser(userId),UserResponse.class);
        }catch (ResponseStatusException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,MessageResponse.USER_NOT_FOUND);
        }
    }

    @Override
    public UserResponse updateUser(@NonNull UUID userId, @NonNull UserRequest userRequest) {
        User user = findUser(userId);
        mapUser(user, userRequest);
        return this.modelMapper.map(this.update(user),UserResponse.class);
    }

    @Override
    public void deleteById(@NonNull UUID userId) {
        try {
            findUser(userId);
        }catch (ResponseStatusException e){
            throw new RuntimeException(e.getMessage());
        }
        this.userRepository.deleteById(userId);
    }

    @Override
    public void delete(@NonNull UserRequest userRequest) {
       UserResponse userResponse = findByUsername(userRequest.getUsername());
        Optional<User> user =this.findAll().stream().filter(u
                        -> u.getUsername().equals(userResponse.getUsername()))
                .findFirst();
        user.ifPresent(this::delete);
    }

    public boolean existById(@NonNull UUID uuid){
        return userRepository.existsById(uuid);
    }
}
