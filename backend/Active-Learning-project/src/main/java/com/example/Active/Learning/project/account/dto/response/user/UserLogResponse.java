package com.example.Active.Learning.project.account.dto.response.user;

import com.example.Active.Learning.project.account.models.enums.ELoginType;
import com.example.Active.Learning.project.account.models.enums.EUserState;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserLogResponse {
    private UUID id;
    private ELoginType eLoginType;
    private EUserState eUserState;
    private UserResponse userResponse;
}
